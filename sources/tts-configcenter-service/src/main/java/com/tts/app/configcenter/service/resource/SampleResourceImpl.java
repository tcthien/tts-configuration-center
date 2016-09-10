package com.tts.app.configcenter.service.resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;

import org.ops4j.pax.cdi.api.OsgiService;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.model.server.ServerDao;
import com.tts.app.configcenter.model.zone.Zone;
import com.tts.app.configcenter.model.zone.ZoneDao;

@Named
@Consumes({"application/json", "test/xml"})
@Produces({"application/json", "test/xml"})
public class SampleResourceImpl {
    
    @OsgiService @Inject
    ZoneDao zoneDao;
    
    @OsgiService @Inject
    ServerDao serverDao;
    
    
    @POST
    public void initSampleData() {
        Zone zone1 = new Zone(1L, "Ubuntu Zone");
        zoneDao.add(zone1);
        Zone zone2 = new Zone(2L, "Dev Zone");
        zoneDao.add(zone2);
        zoneDao.add(new Zone(3L, "Test Zone"));
        
        // Zone 1--------------------------------------------------------------
        Server server = new Server();
        server.setIpAddress("192.168.100.100");
        server.setUserName("tcthien");
        server.setPassword("tcthien");
        server.setServerName("Ubuntu DEV");
        server.setZone(zone1);
        serverDao.add(server);
        
        server = new Server();
        server.setIpAddress("192.168.100.100");
        server.setUserName("tcthien");
        server.setPassword("tcthien");
        server.setServerName("Ubuntu Server 2");
        server.setZone(zone1);
        serverDao.add(server);
        
        server = new Server();
        server.setIpAddress("192.168.100.100");
        server.setUserName("tcthien");
        server.setPassword("tcthien");
        server.setServerName("Ubuntu Server 3");
        server.setZone(zone1);
        serverDao.add(server);
        
        // Zone 2--------------------------------------------------------------
        server = new Server();
        server.setIpAddress("192.168.100.100");
        server.setUserName("tcthien");
        server.setPassword("tcthien");
        server.setServerName("Dev Server 1");
        server.setZone(zone2);
        serverDao.add(server);
        
        server = new Server();
        server.setIpAddress("192.168.100.100");
        server.setUserName("tcthien");
        server.setPassword("tcthien");
        server.setServerName("Dev Server 2");
        server.setZone(zone2);
        serverDao.add(server);
    }

}
