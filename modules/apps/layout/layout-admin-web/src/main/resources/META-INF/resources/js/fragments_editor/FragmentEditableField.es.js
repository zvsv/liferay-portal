import Component from 'metal-component';
import {Config} from 'metal-state';
import {object} from 'metal';
import Soy from 'metal-soy';

import {showImageEditing} from './fragment_processors/EditableImageFragmentProcessor.es';
import {createTextEditor, destroyTextEditor, getActiveEditableElement} from './fragment_processors/EditableTextFragmentProcessor.es';
import templates from './FragmentEditableField.soy';

class FragmentEditableField extends Component {

	/**
	 * @inheritDoc
	 * @review
	 */

	created() {
		this._handleEditableChanged = this._handleEditableChanged.bind(this);
	}

	/**
	 * @inheritDoc
	 * @param {!object} state
	 * @returns {object}
	 */

	prepareStateForRender(state) {
		const translatedContent = this.editableValues[this.languageId] ||
			this.editableValues.defaultValue;

		let content = Soy.toIncDom(translatedContent || this.content);

		if (this.type === 'image' && translatedContent) {
			const tempContent = document.createElement('div');

			tempContent.innerHTML = this.content;

			const tempImage = tempContent.querySelector('img');

			if (tempImage) {
				tempImage.src = translatedContent;
			}

			content = Soy.toIncDom(tempContent.innerHTML);
		}

		return object.mixin(
			{},
			state,
			{content}
		);
	}

	/**
	 * @inheritDoc
	 * @review
	 */

	rendered() {
		if (this._showEditor) {
			if (this.type === 'image') {
				showImageEditing(
					this.refs.editable,
					this.portletNamespace,
					this.imageSelectorURL,
					this.fragmentEntryLinkId,
					this._handleEditableChanged
				);
			}
			else {
				createTextEditor(
					this.refs.editable,
					this.defaultEditorConfiguration,
					this.portletNamespace,
					this.fragmentEntryLinkId,
					this._handleEditableChanged
				);
			}

			this._showEditor = false;
		}
	}

	/**
	 * @inheritDoc
	 * @param changes
	 * @return {boolean}
	 * @review
	 */

	shouldUpdate(changes) {
		return !!changes._showTooltip;
	}

	/**
	 * Handle editable click event
	 * @param {Event} event
	 * @private
	 */

	_handleEditableClick(event) {
		event.preventDefault();
		event.stopPropagation();

		if (getActiveEditableElement() !== this.refs.editable) {
			this._showTooltip = !this._showTooltip;
		}
	}

	/**
	 * Handle edit button click event
	 * @private
	 */

	_handleEditButtonClick() {
		destroyTextEditor();

		this._showTooltip = false;
		this._showEditor = true;
	}

	/**
	 * Handle image editor select event
	 * @param {string} newValue
	 * @private
	 */

	_handleEditableChanged(newValue) {
		this.emit(
			'editableChanged',
			{
				editableId: this.editableId,
				value: newValue
			}
		);
	}

	/**
	 * Handle map button click event
	 * @private
	 */

	_handleMapButtonClick() {
		this._showTooltip = false;

		this.emit(
			'mapButtonClicked',
			{editableId: this.editableId}
		);
	}
}

/**
 * State definition.
 * @review
 * @static
 * @type {!Object}
 */

FragmentEditableField.STATE = {

	/**
	 * Editable content to be rendered
	 * @default undefined
	 * @instance
	 * @memberOf FragmentEditableField
	 * @review
	 * @type {!string}
	 */

	content: Config.string().required(),

	/**
	 * Default configuration for AlloyEditor instances.
	 * @default {}
	 * @instance
	 * @memberOf FragmentEditableField
	 * @review
	 * @type {object}
	 */

	defaultEditorConfiguration: Config.object().value({}),

	/**
	 * Editable ID
	 * @default undefined
	 * @instance
	 * @memberOf FragmentEditableField
	 * @review
	 * @type {!string}
	 */

	editableId: Config.string().required(),

	/**
	 * Editable values
	 * @default undefined
	 * @instance
	 * @memberOf FragmentEditableField
	 * @review
	 * @type {!object}
	 */

	editableValues: Config.object().required(),

	/**
	 * FragmentEntryLink id
	 * @default undefined
	 * @instance
	 * @memberOf FragmentEditableField
	 * @review
	 * @type {!string}
	 */

	fragmentEntryLinkId: Config.string().required(),

	/**
	 * Image selector url
	 * @default undefined
	 * @instance
	 * @memberOf FragmentEditableField
	 * @review
	 * @type {!string}
	 */

	imageSelectorURL: Config.string().required(),

	/**
	 * Currently selected language id.
	 * @default undefined
	 * @instance
	 * @memberOf FragmentsEditor
	 * @review
	 * @type {!string}
	 */

	languageId: Config.string().required(),

	/**
	 * Portlet namespace
	 * @default undefined
	 * @instance
	 * @memberOf FragmentEditableField
	 * @review
	 * @type {!string}
	 */

	portletNamespace: Config.string().required(),

	/**
	 * Editable type
	 * @default undefined
	 * @instance
	 * @memberOf FragmentEditableField
	 * @review
	 * @type {!string}
	 */

	type: Config.string().required(),

	/**
	 * Flag indicating if the editable editor should be enabled.
	 * @default false
	 * @instance
	 * @memberOf FragmentEditableField
	 * @private
	 * @review
	 * @type {boolean}
	 */

	_showEditor: Config.internal().bool().value(false),

	/**
	 * Flag indicating if the click tooltip should be visible.
	 * @default false
	 * @instance
	 * @memberOf FragmentEditableField
	 * @private
	 * @review
	 * @type {boolean}
	 */

	_showTooltip: Config.internal().bool().value(false)
};

Soy.register(FragmentEditableField, templates);

export {FragmentEditableField};
export default FragmentEditableField;