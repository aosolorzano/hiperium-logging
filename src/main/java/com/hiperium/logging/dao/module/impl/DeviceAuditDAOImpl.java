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
package com.hiperium.logging.dao.module.impl;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import com.datastax.driver.core.BoundStatement;
import com.hiperium.commons.services.bean.BeanUtils;
import com.hiperium.commons.services.logger.HiperiumLogger;
import com.hiperium.logging.common.bean.CassandraConnectorBean;
import com.hiperium.logging.dao.module.DeviceAuditDAO;
import com.hiperium.logging.dao.module.model.UserDevice;

/**
 * 
 * @author Andres Solorzano
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class DeviceAuditDAOImpl implements DeviceAuditDAO {
	
	/** The property log. */
	@Inject
	private HiperiumLogger log;
	
	/** The property cassandraSession. */
	@Inject
	private CassandraConnectorBean cassandraConnector;
	
	/** The property insertDeviceAuditBS. */
	private BoundStatement insertDeviceAuditBS;
	
	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		// Prepared statement for device audit register
		this.insertDeviceAuditBS = new BoundStatement(this.cassandraConnector.getInsertDeviceAuditPS());
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Asynchronous
	public void create(@NotNull UserDevice register) {
		this.log.debug("create() - BEGIN");
		register.setId(BeanUtils.uuidForDate(new Date()));
		this.cassandraConnector.getSession().execute(this.insertDeviceAuditBS.bind(
				register.getId(),
				register.getUserId(),
				register.getDeviceId(),
				register.getEventDate(),
				register.getIpConnection(),
				register.getAccessChannel().name(),
				register.getAction().name()
				));
		this.log.debug("create() - END");
	} 
}
