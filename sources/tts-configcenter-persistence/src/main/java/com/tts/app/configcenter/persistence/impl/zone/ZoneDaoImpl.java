package com.tts.app.configcenter.persistence.impl.zone;

import javax.inject.Named;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.ops4j.pax.cdi.api.Properties;
import org.ops4j.pax.cdi.api.Property;

import com.tts.app.configcenter.model.zone.Zone;
import com.tts.app.configcenter.model.zone.ZoneDao;
import com.tts.app.configcenter.model.zone.ZoneQueryFilter;
import com.tts.lib.persistence.impl.generic.GenericDaoImpl;

@OsgiServiceProvider(classes = {ZoneDao.class})
//The properties below allow to transparently export the service as a web service using Distributed OSGi
@Properties({
@Property(name = "service.exported.interfaces", value = "*")
})
@Named(value = "zoneDao")
@Transactional
public class ZoneDaoImpl extends GenericDaoImpl<Zone, ZoneQueryFilter> implements ZoneDao {

    public ZoneDaoImpl() {
        super(Zone.class);
    }

}
