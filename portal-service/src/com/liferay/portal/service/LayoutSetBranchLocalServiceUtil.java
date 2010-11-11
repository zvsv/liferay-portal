/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
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

package com.liferay.portal.service;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.MethodCache;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * The utility for the layout set branch local service. This utility wraps {@link com.liferay.portal.service.impl.LayoutSetBranchLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see LayoutSetBranchLocalService
 * @see com.liferay.portal.service.base.LayoutSetBranchLocalServiceBaseImpl
 * @see com.liferay.portal.service.impl.LayoutSetBranchLocalServiceImpl
 * @generated
 */
public class LayoutSetBranchLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to {@link com.liferay.portal.service.impl.LayoutSetBranchLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the layout set branch to the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSetBranch the layout set branch to add
	* @return the layout set branch that was added
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetBranch addLayoutSetBranch(
		com.liferay.portal.model.LayoutSetBranch layoutSetBranch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().addLayoutSetBranch(layoutSetBranch);
	}

	/**
	* Creates a new layout set branch with the primary key. Does not add the layout set branch to the database.
	*
	* @param layoutSetBranchId the primary key for the new layout set branch
	* @return the new layout set branch
	*/
	public static com.liferay.portal.model.LayoutSetBranch createLayoutSetBranch(
		long layoutSetBranchId) {
		return getService().createLayoutSetBranch(layoutSetBranchId);
	}

	/**
	* Deletes the layout set branch with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSetBranchId the primary key of the layout set branch to delete
	* @throws PortalException if a layout set branch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteLayoutSetBranch(long layoutSetBranchId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteLayoutSetBranch(layoutSetBranchId);
	}

	/**
	* Deletes the layout set branch from the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSetBranch the layout set branch to delete
	* @throws PortalException
	* @throws SystemException if a system exception occurred
	*/
	public static void deleteLayoutSetBranch(
		com.liferay.portal.model.LayoutSetBranch layoutSetBranch)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteLayoutSetBranch(layoutSetBranch);
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query to search with
	* @param start the lower bound of the range of model instances to return
	* @param end the upper bound of the range of model instances to return (not inclusive)
	* @param orderByComparator the comparator to order the results by
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public static java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Counts the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query to search with
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Gets the layout set branch with the primary key.
	*
	* @param layoutSetBranchId the primary key of the layout set branch to get
	* @return the layout set branch
	* @throws PortalException if a layout set branch with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetBranch getLayoutSetBranch(
		long layoutSetBranchId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLayoutSetBranch(layoutSetBranchId);
	}

	/**
	* Gets a range of all the layout set branchs.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of layout set branchs to return
	* @param end the upper bound of the range of layout set branchs to return (not inclusive)
	* @return the range of layout set branchs
	* @throws SystemException if a system exception occurred
	*/
	public static java.util.List<com.liferay.portal.model.LayoutSetBranch> getLayoutSetBranchs(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLayoutSetBranchs(start, end);
	}

	/**
	* Gets the number of layout set branchs.
	*
	* @return the number of layout set branchs
	* @throws SystemException if a system exception occurred
	*/
	public static int getLayoutSetBranchsCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLayoutSetBranchsCount();
	}

	/**
	* Updates the layout set branch in the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSetBranch the layout set branch to update
	* @return the layout set branch that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetBranch updateLayoutSetBranch(
		com.liferay.portal.model.LayoutSetBranch layoutSetBranch)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateLayoutSetBranch(layoutSetBranch);
	}

	/**
	* Updates the layout set branch in the database. Also notifies the appropriate model listeners.
	*
	* @param layoutSetBranch the layout set branch to update
	* @param merge whether to merge the layout set branch with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the layout set branch that was updated
	* @throws SystemException if a system exception occurred
	*/
	public static com.liferay.portal.model.LayoutSetBranch updateLayoutSetBranch(
		com.liferay.portal.model.LayoutSetBranch layoutSetBranch, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().updateLayoutSetBranch(layoutSetBranch, merge);
	}

	public static com.liferay.portal.model.LayoutSetBranch addLayoutSetBranch(
		long userId, long groupId, boolean privateLayout,
		java.lang.String name, java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .addLayoutSetBranch(userId, groupId, privateLayout, name,
			description, serviceContext);
	}

	public static void deleteLayoutSetBranch(
		com.liferay.portal.model.LayoutSetBranch layoutSetBranch,
		boolean includeMaster)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteLayoutSetBranch(layoutSetBranch, includeMaster);
	}

	public static void deleteLayoutSetBranches(long groupId,
		boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService().deleteLayoutSetBranches(groupId, privateLayout);
	}

	public static void deleteLayoutSetBranches(long groupId,
		boolean privateLayout, boolean includeMaster)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		getService()
			.deleteLayoutSetBranches(groupId, privateLayout, includeMaster);
	}

	public static com.liferay.portal.model.LayoutSetBranch getLayoutSetBranch(
		long groupId, boolean privateLayout, java.lang.String name)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getLayoutSetBranch(groupId, privateLayout, name);
	}

	public static java.util.List<com.liferay.portal.model.LayoutSetBranch> getLayoutSetBranches(
		long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.SystemException {
		return getService().getLayoutSetBranches(groupId, privateLayout);
	}

	public static com.liferay.portal.model.LayoutSetBranch getMasterLayoutSetBranch(
		long groupId, boolean privateLayout)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService().getMasterLayoutSetBranch(groupId, privateLayout);
	}

	public static com.liferay.portal.model.LayoutSetBranch updateLayoutSetBranch(
		long layoutSetBranchId, java.lang.String name,
		java.lang.String description,
		com.liferay.portal.service.ServiceContext serviceContext)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return getService()
				   .updateLayoutSetBranch(layoutSetBranchId, name, description,
			serviceContext);
	}

	public static LayoutSetBranchLocalService getService() {
		if (_service == null) {
			_service = (LayoutSetBranchLocalService)PortalBeanLocatorUtil.locate(LayoutSetBranchLocalService.class.getName());

			ReferenceRegistry.registerReference(LayoutSetBranchLocalServiceUtil.class,
				"_service");
			MethodCache.remove(LayoutSetBranchLocalService.class);
		}

		return _service;
	}

	public void setService(LayoutSetBranchLocalService service) {
		MethodCache.remove(LayoutSetBranchLocalService.class);

		_service = service;

		ReferenceRegistry.registerReference(LayoutSetBranchLocalServiceUtil.class,
			"_service");
		MethodCache.remove(LayoutSetBranchLocalService.class);
	}

	private static LayoutSetBranchLocalService _service;
}