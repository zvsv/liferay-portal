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

package com.liferay.portal.kernel.service;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;

/**
 * Provides the local service utility for ClusterGroup. This utility wraps
 * <code>com.liferay.portal.service.impl.ClusterGroupLocalServiceImpl</code> and
 * is an access point for service operations in application layer code running
 * on the local server. Methods of this service will not have security checks
 * based on the propagated JAAS credentials because this service can only be
 * accessed from within the same VM.
 *
 * @author Brian Wing Shun Chan
 * @see ClusterGroupLocalService
 * @generated
 */
@ProviderType
public class ClusterGroupLocalServiceUtil {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify this class directly. Add custom service methods to <code>com.liferay.portal.service.impl.ClusterGroupLocalServiceImpl</code> and rerun ServiceBuilder to regenerate this class.
	 */

	/**
	* Adds the cluster group to the database. Also notifies the appropriate model listeners.
	*
	* @param clusterGroup the cluster group
	* @return the cluster group that was added
	*/
	public static com.liferay.portal.kernel.model.ClusterGroup addClusterGroup(
		com.liferay.portal.kernel.model.ClusterGroup clusterGroup) {
		return getService().addClusterGroup(clusterGroup);
	}

	public static com.liferay.portal.kernel.model.ClusterGroup addClusterGroup(
		String name, java.util.List<String> clusterNodeIds) {
		return getService().addClusterGroup(name, clusterNodeIds);
	}

	public static com.liferay.portal.kernel.model.ClusterGroup addWholeClusterGroup(
		String name) {
		return getService().addWholeClusterGroup(name);
	}

	/**
	* Creates a new cluster group with the primary key. Does not add the cluster group to the database.
	*
	* @param clusterGroupId the primary key for the new cluster group
	* @return the new cluster group
	*/
	public static com.liferay.portal.kernel.model.ClusterGroup createClusterGroup(
		long clusterGroupId) {
		return getService().createClusterGroup(clusterGroupId);
	}

	/**
	* Deletes the cluster group from the database. Also notifies the appropriate model listeners.
	*
	* @param clusterGroup the cluster group
	* @return the cluster group that was removed
	*/
	public static com.liferay.portal.kernel.model.ClusterGroup deleteClusterGroup(
		com.liferay.portal.kernel.model.ClusterGroup clusterGroup) {
		return getService().deleteClusterGroup(clusterGroup);
	}

	/**
	* Deletes the cluster group with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param clusterGroupId the primary key of the cluster group
	* @return the cluster group that was removed
	* @throws PortalException if a cluster group with the primary key could not be found
	*/
	public static com.liferay.portal.kernel.model.ClusterGroup deleteClusterGroup(
		long clusterGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deleteClusterGroup(clusterGroupId);
	}

	/**
	* @throws PortalException
	*/
	public static com.liferay.portal.kernel.model.PersistedModel deletePersistedModel(
		com.liferay.portal.kernel.model.PersistedModel persistedModel)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().deletePersistedModel(persistedModel);
	}

	public static com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return getService().dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.ClusterGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) {
		return getService().dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.ClusterGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	*/
	public static <T> java.util.List<T> dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<T> orderByComparator) {
		return getService()
				   .dynamicQuery(dynamicQuery, start, end, orderByComparator);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery) {
		return getService().dynamicQueryCount(dynamicQuery);
	}

	/**
	* Returns the number of rows matching the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @param projection the projection to apply to the query
	* @return the number of rows matching the dynamic query
	*/
	public static long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery,
		com.liferay.portal.kernel.dao.orm.Projection projection) {
		return getService().dynamicQueryCount(dynamicQuery, projection);
	}

	public static com.liferay.portal.kernel.model.ClusterGroup fetchClusterGroup(
		long clusterGroupId) {
		return getService().fetchClusterGroup(clusterGroupId);
	}

	public static com.liferay.portal.kernel.dao.orm.ActionableDynamicQuery getActionableDynamicQuery() {
		return getService().getActionableDynamicQuery();
	}

	/**
	* Returns the cluster group with the primary key.
	*
	* @param clusterGroupId the primary key of the cluster group
	* @return the cluster group
	* @throws PortalException if a cluster group with the primary key could not be found
	*/
	public static com.liferay.portal.kernel.model.ClusterGroup getClusterGroup(
		long clusterGroupId)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getClusterGroup(clusterGroupId);
	}

	/**
	* Returns a range of all the cluster groups.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code> will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not <code>com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS</code>), then the query will include the default ORDER BY logic from <code>com.liferay.portal.model.impl.ClusterGroupModelImpl</code>. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of cluster groups
	* @param end the upper bound of the range of cluster groups (not inclusive)
	* @return the range of cluster groups
	*/
	public static java.util.List<com.liferay.portal.kernel.model.ClusterGroup> getClusterGroups(
		int start, int end) {
		return getService().getClusterGroups(start, end);
	}

	/**
	* Returns the number of cluster groups.
	*
	* @return the number of cluster groups
	*/
	public static int getClusterGroupsCount() {
		return getService().getClusterGroupsCount();
	}

	public static com.liferay.portal.kernel.dao.orm.IndexableActionableDynamicQuery getIndexableActionableDynamicQuery() {
		return getService().getIndexableActionableDynamicQuery();
	}

	/**
	* Returns the OSGi service identifier.
	*
	* @return the OSGi service identifier
	*/
	public static String getOSGiServiceIdentifier() {
		return getService().getOSGiServiceIdentifier();
	}

	public static com.liferay.portal.kernel.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException {
		return getService().getPersistedModel(primaryKeyObj);
	}

	/**
	* Updates the cluster group in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param clusterGroup the cluster group
	* @return the cluster group that was updated
	*/
	public static com.liferay.portal.kernel.model.ClusterGroup updateClusterGroup(
		com.liferay.portal.kernel.model.ClusterGroup clusterGroup) {
		return getService().updateClusterGroup(clusterGroup);
	}

	public static ClusterGroupLocalService getService() {
		if (_service == null) {
			_service = (ClusterGroupLocalService)PortalBeanLocatorUtil.locate(ClusterGroupLocalService.class.getName());

			ReferenceRegistry.registerReference(ClusterGroupLocalServiceUtil.class,
				"_service");
		}

		return _service;
	}

	private static ClusterGroupLocalService _service;
}