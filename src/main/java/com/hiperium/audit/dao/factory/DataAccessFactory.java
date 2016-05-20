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
package com.hiperium.audit.dao.factory;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;

import com.hiperium.audit.dao.module.DeviceAuditDAO;
import com.hiperium.audit.dao.module.UserActivityDAO;
import com.hiperium.audit.dao.module.UserStatisticDAO;

/**
 * Abstract Factory class that contains references to all DAO objects.
 * 
 * @author Andres Solorzano
 *
 */
@ApplicationScoped
public class DataAccessFactory {

	/** The property deviceAuditDAO */
	@EJB
	private DeviceAuditDAO deviceAuditDAO;

	/** The property userStatisticsDAO */
	@EJB
	private UserStatisticDAO userStatisticsDAO;

	/** The property userActivityDAO */
	@EJB
	private UserActivityDAO userActivityDAO;

	/**
	 * Class constructor.
	 */
	public DataAccessFactory() {
		// Nothing to do
	}

	/**
	 * @return the deviceAuditDAO
	 */
	public DeviceAuditDAO getDeviceAuditDAO() {
		return deviceAuditDAO;
	}

	/**
	 * @return the userStatisticsDAO
	 */
	public UserStatisticDAO getUserStatisticsDAO() {
		return userStatisticsDAO;
	}

	/**
	 * @return the userActivityDAO
	 */
	public UserActivityDAO getUserActivityDAO() {
		return userActivityDAO;
	}

}
