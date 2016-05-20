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

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.hiperium.audit.bo.generic.GenericBO;
import com.hiperium.audit.bo.module.UserStatisticBO;
import com.hiperium.commons.services.logger.HiperiumLogger;
import com.hiperium.commons.services.model.UserStatistic;

/**
 * This service is the implementation of the interface DeviceLocal and manages
 * all actions needed for device management.
 * 
 * @author Andres Solorzano
 * 
 */
@Stateless
public class UserStatisticBOImpl extends GenericBO implements UserStatisticBO {

	/** The property log. */
    @Inject
    protected HiperiumLogger log;
    
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserStatistic findById(@NotNull @Min(value = 1L) Long userId) {
		this.log.debug("findById - START");
		return super.getDaoFactory().getUserStatisticsDAO().findById(userId);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void updateLastPasswordChange(@NotNull @Min(value = 1L) Long userId) {
		this.log.debug("updateLastPasswordChange - START");
		super.getDaoFactory().getUserStatisticsDAO().updateLastPasswordChange(userId);
		this.log.debug("updateLastPasswordChange - END");
	}
}
