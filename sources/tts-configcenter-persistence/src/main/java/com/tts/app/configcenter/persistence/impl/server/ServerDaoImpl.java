package com.tts.app.configcenter.persistence.impl.server;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Named;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.ops4j.pax.cdi.api.Properties;
import org.ops4j.pax.cdi.api.Property;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.model.server.ServerDao;
import com.tts.app.configcenter.model.server.ServerQueryFilter;
import com.tts.lib.persistence.impl.generic.GenericDaoImpl;

@OsgiServiceProvider(classes = {ServerDao.class})
//The properties below allow to transparently export the service as a web service using Distributed OSGi
@Properties({
@Property(name = "service.exported.interfaces", value = "*")
})
@Named(value = "serverDao")
@Transactional
public class ServerDaoImpl extends GenericDaoImpl<Server, ServerQueryFilter> implements ServerDao {

    public ServerDaoImpl() {
        super(Server.class);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    @Override
    public Server findByServerIP(String ipAddress) {
        ServerQueryFilter myFilter = new ServerQueryFilter();
        myFilter.setIpAddress(ipAddress);
        List<Server> lst = findByQuery(myFilter);
        return lst.isEmpty() ? null : lst.iterator().next();
    }
    
    @Override
    protected List<Predicate> decorate(CriteriaBuilder builder, Root<Server> rootQuery, ServerQueryFilter myFilter) {
        List<Predicate> andQueries = new ArrayList<>();
        
        if (myFilter.getIpAddress() != null && !myFilter.getIpAddress().isEmpty()) {
            andQueries.add(builder.equal(rootQuery.get("ipAddress"), myFilter.getIpAddress()));
        }
        if (myFilter.getZoneIds() != null && !myFilter.getZoneIds().isEmpty()) {
            Join<Object, Object> join = rootQuery.join("zone");
            andQueries.add(join.get("id").in(myFilter.getZoneIds()));
        }
        
        return andQueries;
    }
}
