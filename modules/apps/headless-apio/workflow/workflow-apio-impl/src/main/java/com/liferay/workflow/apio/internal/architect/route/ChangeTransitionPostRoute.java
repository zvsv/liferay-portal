/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.workflow.apio.internal.architect.route;

import com.liferay.apio.architect.custom.actions.PostRoute;

/**
 * @author Sarai Díaz
 */
public class ChangeTransitionPostRoute extends PostRoute {

	@Override
	public String getName() {
		return "change-transition";
	}

}