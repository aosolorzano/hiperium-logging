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

import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

import com.datastax.driver.core.Cluster;
import com.hiperium.commons.client.gson.GsonConverterUtil;
import com.hiperium.commons.services.logger.HiperiumLogger;

/**
 * 
 * @author Andres Solorzano
 * @version 1.0
 */
@Startup
@Singleton
@LocalBean
@Lock(LockType.READ)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ConfigurationBean {
	
	/** The LOGGER property for logger messages. */
	private static final HiperiumLogger LOGGER = HiperiumLogger.getLogger(ConfigurationBean.class);
	
	/** The property CASSANDRA_PORT with value apache.cassandra.port. */
	public static final String CASSANDRA_PORT = "apache.cassandra.port";
	/** The property CASSANDRA_HOST with value apache.cassandra.host. */
	public static final String CASSANDRA_HOST = "apache.cassandra.host";
	
	/** The property PROPERTIES. */
	private static final Properties PROPERTIES = new Properties();
	
	/** Cassandra Cluster. */
	private Cluster cluster;
	
	/**
	 * Class initialization.
	 */
	static {
		try {
			PROPERTIES.load(ConfigurationBean.class.getClassLoader().getResourceAsStream("common.properties"));
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
		}
	}
	
	/**
	 * Component initialization.
	 */
	@PostConstruct
	public void init() {
		LOGGER.debug("init() - START");
		// CONNECT TO CASSANDRA CLUSTER
		this.cluster = Cluster.builder().addContactPoint(PROPERTIES.getProperty(CASSANDRA_HOST))
				.withPort(Integer.parseInt(PROPERTIES.getProperty(CASSANDRA_PORT)))
				.build();
		LOGGER.debug("init() - END");
	}
	
	/**
	 * 
	 * @param injectionPoint
	 * @return the cluster
	 */
	@Produces
	public Cluster getCluster(InjectionPoint injectionPoint) {
		return cluster;
	}
	
	/**
	 * Produces the HiperiumLogger component for CDI injection.
	 * 
	 * @param injectionPoint
	 * @return
	 */
	@Produces
	public HiperiumLogger produceHiperiumLogger(InjectionPoint injectionPoint) {
		return HiperiumLogger.getLogger(injectionPoint.getMember().getDeclaringClass());
	}
	
	/**
	 * 
	 * @param injectionPoint
	 * @return
	 */
	@Produces
	public GsonConverterUtil getGsonConverterUtil(InjectionPoint injectionPoint) {
		return new GsonConverterUtil();
	}
	
	/**
	 * Close all opened resources.
	 */
	@PreDestroy
	public void destroy() {
		LOGGER.debug("destroy() - START");
		// DO NOT CLOSE CURATOR CLIENT HERE, IT MUST BE OPENED TO THE END.
		this.cluster.close();
		LOGGER.debug("destroy() - END");
	}
}
