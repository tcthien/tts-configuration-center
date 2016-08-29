package com.tts.app.configcenter.service.impl;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.ops4j.pax.cdi.api.OsgiService;

import com.tts.app.configcenter.model.zone.Zone;
import com.tts.app.configcenter.model.zone.ZoneDao;

@Named
@Consumes({"application/json", "test/xml"})
@Produces({"application/json", "test/xml"})
public class ZoneServiceRest {
    
    @OsgiService @Inject
    ZoneDao zoneDao;
    
    @Context
    UriInfo uri;
    
    @GET
    @Path("/{id}")
    public Response getZone(@PathParam("id") Integer id) {
        Zone zone = zoneDao.get(id);
        return zone == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(zone).build();
    }
}
