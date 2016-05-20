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
package com.hiperium.logging.restful.module;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hiperium.commons.client.dto.DeviceDTO;
import com.hiperium.commons.services.logger.HiperiumLogger;
import com.hiperium.commons.services.restful.path.LoggingRestfulPath;
import com.hiperium.logging.bo.module.DeviceAuditBO;
import com.hiperium.logging.restful.generic.GenericREST;


/**
 * This class represents the service administration Zones with the
 * data base.
 * 
 * @author Andres Solorzano
 * 
 */
@Path(LoggingRestfulPath.DEVICE_AUDIT)
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class DeviceAuditResource extends GenericREST<DeviceDTO> {

	/** The property log. */
    @Inject
    private HiperiumLogger log;

    /** The property deviceAuditBO. */
    @EJB
    private DeviceAuditBO deviceAuditBO;
    
    /**
	 * 
	 * @return
	 * @throws WebApplicationException
	 */
	@POST
	@Path(LoggingRestfulPath.CREATE)
	@Produces(MediaType.TEXT_PLAIN)
	public Response create(@NotNull DeviceDTO deviceDTO) throws WebApplicationException {
		this.log.debug("create - BEGIN");
		this.deviceAuditBO.create(deviceDTO, super.getTokenId());
		this.log.debug("create - END");
		return Response.ok().build();
	}
}
