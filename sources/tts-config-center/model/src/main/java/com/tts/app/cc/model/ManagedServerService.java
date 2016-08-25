package com.tts.app.cc.model;

import java.util.Collection;

public interface ManagedServerService {
    void add(ManagedServer server);

    void update(ManagedServer server);

    void delete(Integer id);

    ManagedServer find(int id);

    Collection<ManagedServer> find();
}
