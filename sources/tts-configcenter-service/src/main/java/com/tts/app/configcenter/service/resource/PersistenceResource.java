package com.tts.app.configcenter.service.resource;

import java.util.Collection;

import javax.ws.rs.core.Response;

import com.tts.lib.model.generic.DataModel;

public interface PersistenceResource<T extends DataModel> {

    Response getObject(Integer id);

    Response addObject(T task);

    Collection<T> getObjects();

    void updateObject(Integer id, T task);

    void deleteObject(Integer id);
}
