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
package com.hiperium.logging.common.bean;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.Session;
import com.hiperium.commons.services.logger.HiperiumLogger;

/**
 * This service class represents the driver connector for the Apache Cassandra
 * instance.
 *
 * @author Andres Solorzano
 *
 */
@Startup
@Singleton
@LocalBean
@Lock(LockType.READ)
@DependsOn("ConfigurationBean")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CassandraConnectorBean {

	/** The property INSER_DEVICE_AUDIT_QUERY. */
	private static final String INSER_DEVICE_AUDIT_QUERY = "INSERT INTO haudit.aud_user_device (id,user_id,device_id,event_date,ip_connection,access_channel,action) "
			+ "VALUES (?,?,?,?,?,?,?);";
	
	/** The property log. */
	@Inject
	private HiperiumLogger log;

	/** Cassandra Cluster. */
	@Inject
	private Cluster cluster;
	/** Cassandra Session. */
	private Session session;
	
	/** The property insertDeviceAuditPS. */
	private PreparedStatement insertDeviceAuditPS;
	
	/**
	 * Component constructor.
	 */
	@PostConstruct
	public void init() {
		this.log.debug("init() - BEGIN");
		final Metadata metadata = this.cluster.getMetadata();
		this.log.debug("Connected to cluster: " + metadata.getClusterName());
		for (final Host host : metadata.getAllHosts()) {
			this.log.debug("Datacenter: " + host.getDatacenter() + " " + host.getAddress() + " " + host.getRack());
		}
		this.session = this.cluster.connect();
		
		// Prepared statement for device audit register
		this.insertDeviceAuditPS = this.session.prepare(INSER_DEVICE_AUDIT_QUERY);
		this.log.debug("init() - END");
	}

	/**
	 * Provide Cassandra Session to be injected in another component.
	 *
	 * @return My session.
	 */
	@Produces
	public Session getSession() {
		return this.session;
	}

	/**
	 * @return the insertDeviceAuditPS
	 */
	public PreparedStatement getInsertDeviceAuditPS() {
		return insertDeviceAuditPS;
	}

}
