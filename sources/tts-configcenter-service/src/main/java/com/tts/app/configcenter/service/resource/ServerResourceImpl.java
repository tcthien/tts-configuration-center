package com.tts.app.configcenter.service.resource;

import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;

import org.ops4j.pax.cdi.api.OsgiService;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.model.server.ServerDao;
import com.tts.app.configcenter.model.server.ServerQueryFilter;

@Named
@Consumes({"application/json", "test/xml"})
@Produces({"application/json", "test/xml"})
public class ServerResourceImpl extends PersistenceResourceImpl<Server, ServerQueryFilter, ServerDao> {
    
    @OsgiService @Inject
    ServerDao serverDao;
    
    @Override
    public ServerDao getDao() {
        return serverDao;
    }
}
