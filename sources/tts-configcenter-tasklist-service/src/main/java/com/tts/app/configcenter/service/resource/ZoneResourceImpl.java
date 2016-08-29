package com.tts.app.configcenter.service.resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.ops4j.pax.cdi.api.OsgiService;

import com.tts.app.configcenter.model.zone.Zone;
import com.tts.app.configcenter.model.zone.ZoneDao;

@Named
@Consumes({"application/json", "test/xml"})
@Produces({"application/json", "test/xml"})
public class ZoneResourceImpl extends PersistenceResourceImpl<Zone, ZoneDao> {
    
    @OsgiService @Inject
    ZoneDao zoneDao;
    
    @Override
    public ZoneDao getDao() {
        return zoneDao;
    }
}
