package com.tts.lib.model.generic;

import java.util.Collection;

public interface GenericDao<T extends DataModel> {

    void add(T obj);
    
    T get(Integer id);
    
    Collection<T> gets();

    void update(T object);

    void delete(Integer id);
}
