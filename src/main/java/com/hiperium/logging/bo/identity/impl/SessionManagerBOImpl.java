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
package com.hiperium.logging.bo.identity.impl;

import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.ejb.DependsOn;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.x.discovery.ServiceDiscovery;
import org.apache.curator.x.discovery.ServiceDiscoveryBuilder;
import org.apache.curator.x.discovery.ServiceInstance;
import org.apache.curator.x.discovery.details.JsonInstanceSerializer;

import com.hiperium.commons.client.dto.ServiceDetailsDTO;
import com.hiperium.commons.client.exception.InformationException;
import com.hiperium.commons.client.registry.path.IdentityRegistryPath;
import com.hiperium.commons.services.vo.UserSessionVO;
import com.hiperium.logging.bo.identity.SessionManagerBO;
import com.hiperium.logging.common.service.IdentityServiceManager;

/**
 * This is a bypass bean that is used between Web components and EJB components
 * but only for some methods that the Web components can see.
 *
 * @author Andres Solorzano
 * @version 1.0
 */
@Startup
@Singleton
@Lock(LockType.READ)
@DependsOn("ConfigurationBean")
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class SessionManagerBOImpl implements SessionManagerBO {

    /** The property identityServiceManager. */
	@EJB
	private IdentityServiceManager identityServiceManager;
	
	/** The property curatorClient. */
	@Inject
	private CuratorFramework curatorClient;
	/** The property serviceDiscovery. */
	private ServiceDiscovery<ServiceDetailsDTO> serviceDiscovery;
	/** The property serializer. */
	private JsonInstanceSerializer<ServiceDetailsDTO> serializer;
	
	/**
	 * 
	 */
	@PostConstruct
	public void init() {
		this.serializer = new JsonInstanceSerializer<ServiceDetailsDTO>(ServiceDetailsDTO.class); // Payload Serializer
		this.serviceDiscovery = ServiceDiscoveryBuilder.builder(ServiceDetailsDTO.class)
				.client(this.curatorClient)
				.basePath(IdentityRegistryPath.BASE_PATH)
				.serializer(this.serializer)
				.build();
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isUserLoggedIn(String userToken) throws InformationException {
		return this.identityServiceManager.isUserLoggedIn(this.getServiceURI(IdentityRegistryPath.IS_USER_LOGGED_IN), userToken);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public UserSessionVO getUserSessionVO(String userToken) throws InformationException {
		return this.identityServiceManager.getUserSessionVO(this.getServiceURI(IdentityRegistryPath.GET_USER_SESSION_VO), userToken);
	}
	
	/**
	 * 
	 * @param serviceRegistryPath
	 * @return
	 * @throws Exception
	 */
	private String getServiceURI(String serviceRegistryPath) throws InformationException {
		Collection<ServiceInstance<ServiceDetailsDTO>> services;
		try {
			services = this.serviceDiscovery.queryForInstances(serviceRegistryPath);
			if(services == null || services.isEmpty()) {
	        	throw new Exception("No results found for querying services called: " + serviceRegistryPath);
	        } else {
	        	for(final ServiceInstance<ServiceDetailsDTO> service : services) {
	        		return service.buildUriSpec();
	            }
	        }
		} catch (Exception e) {
			throw new InformationException(e.getMessage());
		}
		return null;
	}

}