package com.tts.app.cc.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Named;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.ops4j.pax.cdi.api.Properties;
import org.ops4j.pax.cdi.api.Property;

import com.tts.app.cc.model.ManagedServer;
import com.tts.app.cc.model.TTSConfigCenterService;
import com.tts.app.cc.model.Zone;

@OsgiServiceProvider(classes = { TTSConfigCenterService.class })
// The properties below allow to transparently export the service as a web
// service using Distributed OSGi
@Properties({ @Property(name = "service.exported.interfaces", value = "*") })
@Named
@Transactional
public class TTSConfigCenterServiceImpl implements TTSConfigCenterService {
    Map<Integer, Zone> zones = new LinkedHashMap<>();
    Map<Integer, List<ManagedServer>> servers = new LinkedHashMap<>();

    @Override
    public void addZone(Zone zone) {
        zones.put(zone.getId(), zone);
    }

    @Override
    public void addManagedServer(Zone zone, ManagedServer server) {
        List<ManagedServer> lst = servers.get(zone.getId());
        if (lst == null) {
            lst = new ArrayList<>();
        }
        servers.put(zone.getId(), lst);
    }

    @Override
    public List<Zone> findAllZones() {
        return new ArrayList<>(zones.values());
    }

    @Override
    public List<ManagedServer> findServerByZone(int zoneId) {
        return servers.get(zoneId);
    }

    @Override
    public Zone findZone(Integer id) {
        return zones.get(id);
    }

}
