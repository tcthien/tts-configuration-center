package com.tts.app.configcenter.service.resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.ops4j.pax.cdi.api.OsgiService;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.model.server.ServerDao;
import com.tts.app.configcenter.model.util.MassCreation;
import com.tts.app.configcenter.model.zone.Zone;
import com.tts.app.configcenter.model.zone.ZoneDao;

@Named
@Consumes({"application/json", "test/xml"})
@Produces({"application/json", "test/xml"})
public class UtilResourceImpl {
    
    @OsgiService @Inject
    ZoneDao zoneDao;
    
    @OsgiService @Inject
    ServerDao serverDao;
    
    @Context
    UriInfo uri;

    @POST
    @Path("/masscreate")
    public void massCreateZoneAndServers(MassCreation info) {
        // Add new Zone
        Zone zone = new Zone();
        zone.setName(info.getZoneName());
        zone = zoneDao.add(zone);
        
        // Parsing & Add servers
        if (!info.getServers().isEmpty()) {
            String[] servers = info.getServers().split("\n");
            for (String server : servers) {
                String[] serverInfo = server.split(",");

                Server ser = new Server();
                ser.setZone(zone);
                ser.setName(serverInfo[0]);
                ser.setIpAddress(serverInfo[1]);
                ser.setUserName(serverInfo[2]);
                ser.setPassword(serverInfo[3]);
                if (serverInfo.length > 4) {
                    ser.setDescription(serverInfo[4]);
                }
                serverDao.add(ser);
            }
        }
        
    }

}
