/*
 * The contents of this file are subject to the OpenMRS Public License
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.openhmis.workorder;


import org.apache.commons.logging.Log; 
import org.apache.commons.logging.LogFactory;
import org.openmrs.GlobalProperty;
import org.openmrs.api.context.Context;
import org.openmrs.messagesource.MessageSourceService;
import org.openmrs.module.ModuleActivator;
import org.openmrs.module.openhmis.workorder.api.model.WorkOrderType;
import org.openmrs.module.openhmis.workorder.api.util.ModuleConstants;
import org.openmrs.module.openhmis.workorder.api.util.WorkOrderHelper;

/**
 * This class contains the logic that is run every time this module is either started or stopped.
 */
public class WorkOrderModuleActivator implements ModuleActivator {
	
	protected Log log = LogFactory.getLog(getClass());
		
	/**
	 * @see ModuleActivator#willRefreshContext()
	 */
	public void willRefreshContext() {
		log.info("Refreshing OpenHMIS Work Order Module");
	}
	
	/**
	 * @see ModuleActivator#contextRefreshed()
	 */
	public void contextRefreshed() {
		log.info("OpenHMIS Work Order Module refreshed");
	}
	
	/**
	 * @see ModuleActivator#willStart()
	 */
	public void willStart() {
		log.info("Starting OpenHMIS Work Order Module");
	}
	
	/**
	 * @see ModuleActivator#started()
	 */
	public void started() {
		log.info("OpenHMIS Work Order Module started");
		setupDefaultWorkOrderType();
	}
	
	/**
	 * @see ModuleActivator#willStop()
	 */
	public void willStop() {
		log.info("Stopping OpenHMIS Work Order Module");
	}
	
	/**
	 * @see ModuleActivator#stopped()
	 */
	public void stopped() {
		log.info("OpenHMIS Work Order Module stopped");
	}
	
	private void setupDefaultWorkOrderType() {
		GlobalProperty defaultProperty = new GlobalProperty(ModuleConstants.DEFAULT_WORKORDER_TYPE_UUID_PROPERTY, null);

		if (!WorkOrderHelper.checkWorkOrderType(defaultProperty)) {
			MessageSourceService messages = Context.getMessageSourceService();

			WorkOrderType workOrderType = new WorkOrderType();
			workOrderType.setName(messages.getMessage("openhmis.workorder.defaultWorkOrderTypeName"));
			workOrderType.setDescription(messages.getMessage("openhmis.workorder.defaultWorkOrderTypeDescription"));

			WorkOrderHelper.ensureWorkOrderType(workOrderType, defaultProperty);
		}
	}
}
