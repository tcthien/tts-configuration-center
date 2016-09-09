package com.tts.lib.model.generic;

import java.util.Collection;
import java.util.List;

import com.tts.app.configcenter.model.util.QueryFilter;

public interface GenericDao<T extends DataModel, QUERY extends QueryFilter> {

    T add(T obj);
    
    T get(Long id);
    
    Collection<T> gets();

    void update(T object);

    void delete(Long id);
    
    List<T> findByQuery(QUERY filter);
}
