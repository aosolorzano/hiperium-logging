/**
 * Product  : Hiperium Project
 * Architect: Andres Solorzano.
 * Created  : 08-05-2009 - 23:30:00
 * 
 * The contents of this file are copyrighted by Andres Solorzano 
 * and it is protected by the license: "GPL V3." You can find a copy of this 
 * license at: http://www.hiperium.com/about/licence.html
 * 
 * Copyright 2014 Andres Solorzano. All rights reserved.
 * 
 */
package com.hiperium.audit.bo.module.impl;

import java.util.Date;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import com.hiperium.audit.bo.generic.GenericBO;
import com.hiperium.audit.bo.module.DeviceAuditBO;
import com.hiperium.audit.dao.module.model.UserDevice;
import com.hiperium.commons.client.dto.DeviceDTO;
import com.hiperium.commons.services.EnumDeviceAction;
import com.hiperium.commons.services.logger.HiperiumLogger;
import com.hiperium.commons.services.vo.UserSessionVO;

/**
 * This service is the implementation of the interface DeviceLocal and manages
 * all actions needed for device management.
 * 
 * @author Andres Solorzano
 * 
 */
@Stateless
public class DeviceAuditBOImpl extends GenericBO implements DeviceAuditBO {

	/** The property log. */
    @Inject
    protected HiperiumLogger log;
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(@NotNull DeviceDTO deviceDTO, String tokenID) {
		this.log.debug("create - START");
		UserSessionVO auditDTO;
		try {
			auditDTO = super.getSessionManagerBO().getUserSessionVO(tokenID);
			if(auditDTO == null) {
				this.log.error("SESSION AUDIT RETURN NULL FROM SESSION MANAGER.");
			} else {
				UserDevice deviceAudit = new UserDevice(auditDTO.getUserId(), deviceDTO.getId());
				deviceAudit.setAction(deviceDTO.getStatus()? EnumDeviceAction.ACTIVATE : EnumDeviceAction.DEACTIVATE);
				deviceAudit.setEventDate(new Date());
				deviceAudit.setIpConnection(auditDTO.getIpConnection());
				deviceAudit.setAccessChannel(auditDTO.getAccessChannel());
				super.getDaoFactory().getDeviceAuditDAO().create(deviceAudit);
			}
		} catch (Exception e) {
			this.log.error(e.getMessage());
		}
		this.log.debug("create - END");
	}
}
