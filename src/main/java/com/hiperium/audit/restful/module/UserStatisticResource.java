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
package com.hiperium.audit.restful.module;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hiperium.audit.bo.module.UserStatisticBO;
import com.hiperium.audit.restful.generic.GenericREST;
import com.hiperium.commons.services.logger.HiperiumLogger;
import com.hiperium.commons.services.model.UserStatistic;
import com.hiperium.commons.services.restful.path.LoggingRestfulPath;


/**
 * This class represents the service administration Zones with the
 * data base.
 * 
 * @author Andres Solorzano
 * 
 */
@Path(LoggingRestfulPath.USER_STATISTICS)
public class UserStatisticResource extends GenericREST<UserStatistic> {

	/** The property log. */
    @Inject
    private HiperiumLogger log;

    /** The property userStatisticBO. */
    @EJB
    private UserStatisticBO userStatisticBO;
    
    /**
	 * 
	 * @return
	 * @throws WebApplicationException
	 */
	@GET
	@Path(LoggingRestfulPath.FIND_USER_STATISTIC)
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public UserStatistic findById(@QueryParam("id") Long id) throws WebApplicationException {
		this.log.debug("findById - BEGIN");
		return this.userStatisticBO.findById(id);
	}
	
	/**
	 * 
	 * @return
	 * @throws WebApplicationException
	 */
	@PUT
	@Path(LoggingRestfulPath.UPDATE_LAST_PASSWD)
	@Consumes(MediaType.TEXT_PLAIN)
	@Produces(MediaType.TEXT_PLAIN)
	public Response updateLastPasswordChange(@NotNull @Min(value = 1L) Long id) throws WebApplicationException {
		this.log.debug("updateLastPasswordChange - BEGIN");
		this.userStatisticBO.updateLastPasswordChange(id);
		this.log.debug("updateLastPasswordChange - END");
		return Response.ok().build();
	}
}
