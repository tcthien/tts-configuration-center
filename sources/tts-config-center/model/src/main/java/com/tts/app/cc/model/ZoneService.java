package com.tts.app.cc.model;

import java.util.Collection;

public interface ZoneService {
    void addZone(Zone zone);

    void updateZone(Zone zone);

    void deleteZone(Integer id);

    Zone getZone(int id);

    Collection<Zone> getZones();
}
