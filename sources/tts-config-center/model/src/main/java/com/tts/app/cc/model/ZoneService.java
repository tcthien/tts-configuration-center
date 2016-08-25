package com.tts.app.cc.model;

import java.util.Collection;

public interface ZoneService {
    void add(Zone zone);

    void update(Zone zone);

    void delete(Integer id);

    Zone find(int id);

    Collection<Zone> find();
}
