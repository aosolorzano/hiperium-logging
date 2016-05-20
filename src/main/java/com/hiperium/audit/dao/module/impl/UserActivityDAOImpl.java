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
package com.hiperium.audit.dao.module.impl;

import java.util.Date;

import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;
import com.hiperium.audit.dao.module.UserActivityDAO;
import com.hiperium.audit.dao.module.model.UserActivity;
import com.hiperium.commons.services.bean.BeanUtils;
import com.hiperium.commons.services.logger.HiperiumLogger;

/**
 * 
 * @author Andres Solorzano
 * 
 */
@Stateless
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class UserActivityDAOImpl implements UserActivityDAO {

	/** The property log. */
	@Inject
	private HiperiumLogger log;
	
	/** The property cassandraSession. */
	@Inject
	private Session cassandraSession;
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	@Asynchronous
	public void create(@NotNull UserActivity register) {
		this.log.debug("create() - BEGIN");
		Mapper<UserActivity> mapper = new MappingManager(this.cassandraSession).mapper(UserActivity.class);
		register.setId(BeanUtils.uuidForDate(new Date()));
		mapper.save(register);
		this.log.debug("create() - END");
	} 
}
