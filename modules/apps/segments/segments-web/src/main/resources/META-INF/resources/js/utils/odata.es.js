import {
	CONJUNCTIONS,
	FUNCTIONAL_OPERATORS,
	GROUP,
	NOT_OPERATORS,
	RELATIONAL_OPERATORS
} from './constants.es';
import {generateGroupId} from './utils.es';
import '../libs/odata-parser.js';

const OPERATORS = {
	...FUNCTIONAL_OPERATORS,
	...RELATIONAL_OPERATORS
};

const oDataFilterFn = window.oDataParser.filter;

/**
 * Maps Odata-v4-parser generated AST expression names to internally used
 * constants.
 */
const oDataV4ParserNameMap = {
	AndExpression: CONJUNCTIONS.AND,
	BoolParenExpression: GROUP,
	contains: OPERATORS.CONTAINS,
	EqualsExpression: OPERATORS.EQ,
	GreaterOrEqualsExpression: OPERATORS.GE,
	GreaterThanExpression: OPERATORS.GT,
	LesserOrEqualsExpression: OPERATORS.LE,
	LesserThanExpression: OPERATORS.LT,
	OrExpression: CONJUNCTIONS.OR
};

/**
 * Wraps a node in a grouping node.
 * @param {object} oDataASTNode
 * @param {string} prevConjunction
 * @returns Object representing the grouping
 */
function addNewGroup({oDataASTNode, prevConjunction}) {
	return {
		lastNodeWasGroup: false,
		oDataASTNode: {
			type: 'BoolParenExpression',
			value: oDataASTNode
		},
		prevConjunction
	};
}

/**
 * Recursively traverses the criteria object to build an oData filter query
 * string.
 * @param {object} criteria
 * @param {string} queryConjunction
 * @returns An OData query string built from the criteria object.
 */
function buildQueryString(criteria, queryConjunction) {
	return criteria
		.filter(Boolean)
		.reduce(
			(queryString, criterion, index) => {
				const {
					conjunctionName,
					items,
					operatorName,
					propertyName,
					value
				} = criterion;

				if (index > 0) {
					queryString = queryString.concat(` ${queryConjunction} `);
				}

				if (conjunctionName) {
					queryString = queryString.concat(
						`(${buildQueryString(items, conjunctionName)})`
					);
				}
				else if (isValueType(RELATIONAL_OPERATORS, operatorName)) {
					queryString = queryString.concat(
						`${propertyName} ${operatorName} '${value}'`
					);
				}
				else if (isValueType(FUNCTIONAL_OPERATORS, operatorName)) {
					queryString = queryString.concat(
						`${operatorName}(${propertyName}, '${value}')`
					);
				}
				else if (isValueType(NOT_OPERATORS, operatorName)) {
					const baseOperator = operatorName.replace(/not-/g, '');

					const baseExpression = [{
						operatorName: baseOperator,
						propertyName,
						value
					}]

					//Not is wrapped in a group to simplify AST parsing
					queryString = queryString.concat(
						`(not (${buildQueryString(baseExpression)}))`
					);
				}

				return queryString;
			},
			''
		);
}

/**
 * Gets the internal name of a child expression from the oDataV4Parser name
 * @param {object} oDataASTNode
 * @returns String value of the internal name.
 */
function getChildExpressionName(oDataASTNode) {
	return getExpressionName(oDataASTNode.value);
}

/**
 * Gets the conjunction of the group or returns AND as a default.
 * @param {object} oDataASTNode
 * @returns The conjunction name for a group or, if not available, AND.
 */
function getConjunctionForGroup(oDataASTNode) {
	const childExpressionName = getChildExpressionName(oDataASTNode);

	return isValueType(CONJUNCTIONS, childExpressionName) ? childExpressionName : CONJUNCTIONS.AND;
}

/**
 * Gets the internal name of an expression from the oDataV4Parser name.
 * @param {object} oDataASTNode
 * @returns String value of the internal name
 */
function getExpressionName(oDataASTNode) {
	const type = oDataASTNode.type;

	let returnValue = oDataV4ParserNameMap[type];

	if (type == 'MethodCallExpression') {
		returnValue = oDataASTNode.value.method;
	}

	return returnValue;
}

function getFunctionName(oDataASTNode) {
	return oDataV4ParserNameMap[oDataASTNode.value.method]
}

/**
 * Returns the next expression in the syntax tree that is not a grouping.
 * @param {object} oDataASTNode
 * @returns String value of the internal name of the next expression.
 */
const getNextNonGroupExpression = oDataASTNode => {
	let returnValue;

	if (oDataASTNode.value.type === 'BoolParenExpression') {
		returnValue = getNextNonGroupExpression(oDataASTNode.value);
	}
	else {
		returnValue = oDataASTNode.value.left ?
			oDataASTNode.value.left :
			oDataASTNode.value;
	}

	return returnValue;
};

/**
 * Returns the next expression in the syntax tree that is not a grouping.
 * @param {object} oDataASTNode
 * @returns String value of the internal name of the next expression.
 */
const getNextOperatorExpression = oDataASTNode => {
	let returnValue;

	const nextNode = oDataASTNode.value.left ?
		oDataASTNode.value.left :
		oDataASTNode.value;

	const type = nextNode.type;

	if (type === 'BoolParenExpression' || type === 'AndExpression' || type === 'OrExpression') {
		returnValue = getNextOperatorExpression(nextNode);
	}
	else {
		returnValue = nextNode
	}

	return returnValue;
};

/**
 * Checks if a grouping has different conjunctions (e.g. (x AND y OR z)).
 * @param {boolean} lastNodeWasGroup
 * @param {object} oDataASTNode
 * @param {string} prevConjunction
 * @returns boolean of whether a grouping has different conjunctions.
 */
function hasDifferentConjunctions({lastNodeWasGroup, oDataASTNode, prevConjunction}) {
	return prevConjunction !== oDataASTNode.type && !lastNodeWasGroup;
}

/**
 * Checks if the criteria is a group by checking if it has an `items` property.
 * @param {object} criteria
 */
function isCriteriaGroup(criteria) {
	return !!criteria.items;
}

/**
 * Checks if the value is a certain type.
 * @param {object} types A map of supported types.
 * @param {*} value The value to validate.
 * @returns {boolean}
 */
function isValueType(types, value) {
	return Object.values(types).includes(value);
}

/**
 * Checks if the group is needed; It is unnecessary when there are multiple
 * groupings in a row, when the conjunction directly outside the group is the
 * same as the one inside or there is no conjunction within a grouping.
 * @param {boolean} lastNodeWasGroup
 * @param {object} oDataASTNode
 * @param {string} prevConjunction
 * @returns a boolean of whether a group is necessary.
 */
function isRedundantGroup({lastNodeWasGroup, oDataASTNode, prevConjunction}) {
	const nextNodeExpressionName = getExpressionName(
		getNextNonGroupExpression(oDataASTNode)
	);

	return lastNodeWasGroup ||
		oDataV4ParserNameMap[prevConjunction] === nextNodeExpressionName ||
		!isValueType(CONJUNCTIONS, nextNodeExpressionName);
}

/**
 * Removes a grouping node and returns the child node
 * @param {object} oDataASTNode
 * @param {string} prevConjunction
 * @returns Object representing the operation inside the grouping
 */
function skipGroup({oDataASTNode, prevConjunction}) {
	return {
		lastNodeWasGroup: true,
		oDataASTNode: oDataASTNode.value,
		prevConjunction
	};
}

/**
 * Converts an OData filter query string to an object that can be used by the
 * criteria builder
 * @param {string} queryString
 * @returns {object} Criteria representation of the query string
 */
function translateQueryToCriteria(queryString) {
	let criteria;

	try {
		if (queryString === '()') {
			throw 'queryString is ()';
		}
		const oDataASTNode = oDataFilterFn(queryString);

		const criteriaArray = toCriteria({oDataASTNode});

		criteria = isCriteriaGroup(criteriaArray[0]) ?
			criteriaArray[0] :
			wrapInCriteriaGroup(criteriaArray);
	}
	catch (e) {
		criteria = null;
	}

	return criteria;
}

/**
 * Recursively transforms the AST generated by the odata-v4-parser library into
 * a shape the criteria builder expects. Returns an array so that left and right
 * arguments can be concatenated together.
 * @param {object} context
 * @param {object} context.oDataASTNode
 * @returns Criterion representation of an AST expression node in an array
 */
function toCriteria(context) {
	const {oDataASTNode} = context;

	const expressionName = getExpressionName(oDataASTNode);

	let criterion;

	if (isValueType(OPERATORS, expressionName)) {
		criterion = transformOperatorNode(context);
	}
	else if (oDataASTNode.type === 'NotExpression') {
		criterion = transformNotNode(context);
	}
	else if (oDataASTNode.type === 'MethodCallExpression') {
		criterion = transformFunctionalNode(context);
	}
	else if (isValueType(CONJUNCTIONS, expressionName)) {
		criterion = transformConjunctionNode(context);
	}
	else if (expressionName === GROUP) {
		criterion = transformGroupNode(context);
	}

	return criterion;
}

/**
 * Transforms conjunction expression node into a criterion for the criteria
 * builder. If it comes across a grouping sharing an AND and OR conjunction, it
 * will add a new grouping so the criteria builder doesn't require a user to
 * know operator precedence.
 * @param {object} context
 * @param {object} context.oDataASTNode
 * @returns an array containing the concatenated left and right values of a
 * conjunction expression or a new grouping.
 */
function transformConjunctionNode(context) {
	const {oDataASTNode} = context;

	const conjunctionType = oDataASTNode.type;
	const nextNode = oDataASTNode.value;

	return hasDifferentConjunctions(context) ?
		toCriteria(addNewGroup(context)) :
		[...toCriteria(
			{
				oDataASTNode: nextNode.left,
				prevConjunction: conjunctionType
			}
		),
		...toCriteria(
			{
				oDataASTNode: nextNode.right,
				prevConjunction: conjunctionType
			}
		)];
}

/**
 * Transform a function expression node into a criterion for the criteria
 * builder.
 * @param {object} oDataASTNode
 * @returns an array containing the object representation of an operator
 * criterion
 */
function transformFunctionalNode({oDataASTNode}) {
	return [
		{
			operatorName: getFunctionName(oDataASTNode),
			propertyName: oDataASTNode.value.parameters[0].raw,
			value: oDataASTNode.value.parameters[1].raw.replace(/['"]+/g, '')
		}
	];
}

/**
 * Transforms a group expression node into a criterion for the criteria
 * builder. If it comes across a grouping that is redundant (doesn't provide
 * readability improvements, superfluous to order of operations), it will remove
 * it.
 * @param {object} context
 * @param {object} context.oDataASTNode
 * @param {string} context.prevConjunction
 * @returns Criterion representation of an AST expression node in an array
 */
function transformGroupNode(context) {
	const {oDataASTNode, prevConjunction} = context;

	return isRedundantGroup(context) ?
		toCriteria(skipGroup(context)) :
		[{
			conjunctionName: getConjunctionForGroup(oDataASTNode),
			groupId: generateGroupId(),
			items: toCriteria(
				{
					lastNodeWasGroup: true,
					oDataASTNode: oDataASTNode.value,
					prevConjunction
				}
			)
		}];
}

/**
 * Transform an operator expression node into a criterion for the criteria
 * builder.
 * @param {object} oDataASTNode
 * @returns an array containing the object representation of an operator
 * criterion
 */
function transformNotNode({oDataASTNode}) {
	const nextNodeExpression = getNextOperatorExpression(oDataASTNode);

	const nextNodeExpressionName = getExpressionName(nextNodeExpression);

	let returnValue;

	if (nextNodeExpressionName == OPERATORS.CONTAINS) {
		returnValue = [
			{
				operatorName: NOT_OPERATORS.NOT_CONTAINS,
				propertyName: nextNodeExpression.value.parameters[0].raw,
				value: nextNodeExpression.value.parameters[1].raw.replace(/['"]+/g, '')
			}
		]
	}
	else if (nextNodeExpressionName == OPERATORS.EQ) {
		returnValue = [
			{
				operatorName: NOT_OPERATORS.NOT_EQ,
				propertyName: nextNodeExpression.value.left.raw,
				value: nextNodeExpression.value.right.raw.replace(/['"]+/g, '')
			}
		]
	}

	return returnValue;
}

/**
 * Transform an operator expression node into a criterion for the criteria
 * builder.
 * @param {object} oDataASTNode
 * @returns an array containing the object representation of an operator
 * criterion
 */
function transformOperatorNode({oDataASTNode}) {
	return [
		{
			operatorName: getExpressionName(oDataASTNode),
			propertyName: oDataASTNode.value.left.raw,
			value: oDataASTNode.value.right.raw.replace(/['"]+/g, '')
		}
	];
}

/**
 * Wraps the criteria items in a criteria group.
 * @param {array} criteriaArray The list of criterion.
 */
function wrapInCriteriaGroup(criteriaArray) {
	return {
		conjunctionName: CONJUNCTIONS.AND,
		groupId: generateGroupId(),
		items: criteriaArray
	};
}

export {
	buildQueryString,
	translateQueryToCriteria
};