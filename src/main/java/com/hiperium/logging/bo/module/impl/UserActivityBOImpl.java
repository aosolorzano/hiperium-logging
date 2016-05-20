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
package com.hiperium.logging.bo.module.impl;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import com.hiperium.commons.services.logger.HiperiumLogger;
import com.hiperium.logging.bo.generic.GenericBO;
import com.hiperium.logging.bo.module.UserActivityBO;
import com.hiperium.logging.dao.module.model.UserActivity;

/**
 * This service is the implementation of the interface DeviceLocal and manages
 * all actions needed for device management.
 * 
 * @author Andres Solorzano
 * 
 */
@Stateless
public class UserActivityBOImpl extends GenericBO implements UserActivityBO {

	/** The property log. */
    @Inject
    protected HiperiumLogger log;
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void create(@NotNull UserActivity register) {
		this.log.debug("create - START");
		super.getDaoFactory().getUserActivityDAO().create(register); 
		this.log.debug("create - END");
	}
}
