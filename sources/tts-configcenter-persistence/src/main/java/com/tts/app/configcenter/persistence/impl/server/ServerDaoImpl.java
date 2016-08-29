package com.tts.app.configcenter.persistence.impl.server;

import javax.inject.Named;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.ops4j.pax.cdi.api.Properties;
import org.ops4j.pax.cdi.api.Property;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.model.server.ServerDao;
import com.tts.lib.persistence.impl.generic.GenericDaoImpl;

@OsgiServiceProvider(classes = {ServerDao.class})
//The properties below allow to transparently export the service as a web service using Distributed OSGi
@Properties({
@Property(name = "service.exported.interfaces", value = "*")
})
@Named(value = "serverDao")
@Transactional
public class ServerDaoImpl extends GenericDaoImpl<Server> implements ServerDao {

    public ServerDaoImpl() {
        super(Server.class);
    }

}
