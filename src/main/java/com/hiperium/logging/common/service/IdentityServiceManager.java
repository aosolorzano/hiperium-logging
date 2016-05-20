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
package com.hiperium.logging.common.service;

import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;

import com.hiperium.commons.client.exception.InformationException;
import com.hiperium.commons.client.http.HttpClient;
import com.hiperium.commons.services.logger.HiperiumLogger;
import com.hiperium.commons.services.vo.UserSessionVO;
import com.hiperium.logging.service.converter.UserSessionConverter;

/**
 * This service class send calls to the REST Service to operate with different
 * actions originated from the system.
 * 
 * @author Andres Solorzano
 * 
 */
@Startup
@Singleton
@LocalBean
@Lock(LockType.READ)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class IdentityServiceManager extends HttpClient {

	/** The LOGGER property for logger messages. */
	private static final HiperiumLogger LOGGER = HiperiumLogger.getLogger(IdentityServiceManager.class);
    
	/** The property sessionAuditConverter. */
	private UserSessionConverter sessionAuditConverter;
	
	/**
	 * Class constructor.
	 */
	public IdentityServiceManager() {
		super();
		this.sessionAuditConverter = new UserSessionConverter();
	}
	
	/**
	 * 
	 * @param serviceURI
	 * @param userToken
	 * @return
	 */
	public boolean isUserLoggedIn(@NotNull String serviceURI, @NotNull String userToken) {
		LOGGER.debug("isUserLoggedIn - START");
		try {
			super.getFromService(serviceURI, "application/json", userToken);
			return true;
		} catch (InformationException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return false;
	}
	
	/**
	 * 
	 * @param serviceURI
	 * @param userToken
	 * @param appToken
	 * @return
	 */
	public UserSessionVO getUserSessionVO(@NotNull String serviceURI, @NotNull String userToken) {
		LOGGER.debug("getSessionAuditVO - START");
		String response;
		try {
			response = super.getFromService(serviceURI, "application/json", userToken);
			if(StringUtils.isNotBlank(response)) {
				UserSessionVO obj = this.sessionAuditConverter.fromJsonToUserSessionVO(response);
				return obj;
			}
		} catch (InformationException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return null;
	}
}
