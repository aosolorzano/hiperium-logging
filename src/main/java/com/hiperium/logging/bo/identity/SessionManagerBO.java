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
package com.hiperium.logging.bo.identity;

import javax.ejb.Local;

import com.hiperium.commons.client.exception.InformationException;
import com.hiperium.commons.services.vo.UserSessionVO;

/**
 * This interface declare the methods needed to zone operations.
 * 
 * @author Andres Solorzano
 * 
 */
@Local
public interface SessionManagerBO {

	/**
	 * 
	 * @param userToken
	 * @return
	 * @throws InformationException
	 */
	boolean isUserLoggedIn(String userToken) throws InformationException;
	
	/**
	 * 
	 * @param userToken
	 * @return
	 * @throws InformationException
	 */
	UserSessionVO getUserSessionVO(String userToken) throws InformationException;
}
