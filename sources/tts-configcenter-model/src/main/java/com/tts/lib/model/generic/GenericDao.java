package com.tts.lib.model.generic;

public interface GenericDao<T extends DataModel> {

    void add(T obj);
    
    T get(Integer id);

    void update(T object);

    void delete(Integer id);
}
