package com.tts.app.cc.rest;

import java.net.URI;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.ops4j.pax.cdi.api.OsgiService;

import com.tts.app.cc.model.Zone;
import com.tts.app.cc.model.ZoneService;

@Path("/zone")
@Named
@Consumes({ "application/json", "application/xml" })
@Produces({ "application/json", "application/xml" })
public class ZoneServiceRest {

    @OsgiService
    @Inject
    ZoneService zoneService;

    @Context
    UriInfo uri;

    @GET
    @Path("{id}")
    public Response getZone(@PathParam("id") Integer id) {
        Zone zone = zoneService.getZone(id);
        return zone == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(zone).build();
    }

    @POST
    public Response addZone(Zone zone) {
        zoneService.addZone(zone);
        URI zoneURI = uri.getRequestUriBuilder().path(ZoneServiceRest.class, "getZone").build(zone.getId());
        return Response.created(zoneURI).build();
    }

    @GET
    public Collection<Zone> getZones() {
        return zoneService.getZones();
    }

    @PUT
    @Path("{id}")
    public void updateZone(@PathParam("id") Integer id, Zone zone) {
        zone.setId(id);
        zoneService.updateZone(zone);
    }

    @DELETE
    @Path("{id}")
    public void deleteZone(@PathParam("id") Integer id) {
        zoneService.deleteZone(id);
    }

}
