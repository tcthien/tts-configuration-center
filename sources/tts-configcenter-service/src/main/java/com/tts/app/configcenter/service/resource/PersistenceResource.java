package com.tts.app.configcenter.service.resource;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.core.Response;

import com.tts.app.configcenter.model.util.QueryFilter;
import com.tts.lib.model.generic.DataModel;

public interface PersistenceResource<T extends DataModel, QUERY extends QueryFilter> {

    Response getObject(Long id);

    Response addObject(T task);

    Collection<T> getObjects();

    void updateObject(Long id, T task);

    void deleteObject(Long id);
    
    List<T> findByQuery(QUERY filter);
}
