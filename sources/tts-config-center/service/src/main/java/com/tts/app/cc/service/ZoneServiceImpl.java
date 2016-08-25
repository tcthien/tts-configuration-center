package com.tts.app.cc.service;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.inject.Named;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.ops4j.pax.cdi.api.Properties;
import org.ops4j.pax.cdi.api.Property;

import com.tts.app.cc.model.Zone;
import com.tts.app.cc.model.ZoneService;

@OsgiServiceProvider(classes = {ZoneService.class})
// The properties below allow to transparently export the service as a web service using Distributed OSGi
@Properties({
    @Property(name = "service.exported.interfaces", value = "*")
})
@Named
public class ZoneServiceImpl implements ZoneService {
    
    int id = 1;
    Map<Integer, Zone> zones = new LinkedHashMap<Integer, Zone>();

    @Override
    public void add(Zone zone) {
        zone.setId(id);
        zones.put(id, zone);
        id++;
    }

    @Override
    public void update(Zone zone) {
        zones.put(zone.getId(), zone);
    }

    @Override
    public void delete(Integer id) {
        zones.remove(id);
    }

    @Override
    public Zone find(int id) {
        return zones.get(id);
    }

    @Override
    public Collection<Zone> find() {
        return zones.values();
    }
    

}
