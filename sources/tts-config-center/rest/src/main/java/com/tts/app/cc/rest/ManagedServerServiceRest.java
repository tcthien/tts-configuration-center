package com.tts.app.cc.rest;

import java.net.URI;
import java.util.Collection;
import java.util.List;

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

import com.tts.app.cc.model.ManagedServer;
import com.tts.app.cc.model.TTSConfigCenterService;
import com.tts.app.cc.model.Zone;

@Path("/server")
@Named
@Consumes({ "application/json", "application/xml" })
@Produces({ "application/json", "application/xml" })
public class ManagedServerServiceRest {

    @OsgiService
    @Inject
    TTSConfigCenterService configService;

    @Context
    UriInfo uri;

    @POST
    @Path("{zoneId}")
    public Response add(@PathParam("zoneId") int zoneId, ManagedServer server) {
        Zone zone = configService.findZone(zoneId);
        configService.addManagedServer(zone, server);
        URI zoneURI = uri.getRequestUriBuilder().path(ManagedServerServiceRest.class, "getZone").build(zone.getId());
        return Response.created(zoneURI).build();
    }

    @GET
    @Path("{zoneId}")
    public Response gets(@PathParam("zoneId") int zoneId) {
        List<ManagedServer> servers = configService.findServerByZone(zoneId);
        return servers == null ? Response.status(Status.NOT_FOUND).build() : Response.ok(servers).build();
    }

    @GET
    public Collection<Zone> getZones() {
        return configService.findAllZones();
    }

    @PUT
    @Path("{id}")
    public void update(@PathParam("id") Integer id, Zone zone) {
        zone.setId(id);
        throw new RuntimeException("Not yet implemented!");
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Integer id) {
        throw new RuntimeException("Not yet implemented!");
    }

}
