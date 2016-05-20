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
package com.hiperium.logging.service.converter;

import com.hiperium.commons.client.gson.GsonConverterUtil;
import com.hiperium.commons.services.vo.UserSessionVO;

/**
 * This is a utility class for serializing or deserializing java objects in a
 * JSON format.
 * 
 * @author Andres Solorzano
 */
public final class UserSessionConverter extends GsonConverterUtil {

	/**
	 * Default constructor.
	 */
	public UserSessionConverter() {
		super();
	}
	
	/**
	 * 
	 * @param json
	 * @return
	 */
	public UserSessionVO fromJsonToUserSessionVO(String json) {
		return super.getGson().fromJson(json, UserSessionVO.class);
	}
}
