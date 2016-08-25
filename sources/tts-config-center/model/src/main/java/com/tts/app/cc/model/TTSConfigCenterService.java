package com.tts.app.cc.model;

import java.util.List;

public interface TTSConfigCenterService {
    void addZone(Zone zone);
    
    void addManagedServer(Zone zone, ManagedServer server);
    
    List<Zone> findAllZones();
    
    Zone findZone(Integer id);
    
    List<ManagedServer> findServerByZone(int zoneId);

}
