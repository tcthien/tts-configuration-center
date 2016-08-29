package com.tts.lib.dao.generic;

public interface GenericDao<T extends DataModel> {

    T get(Integer id);

    void update(T object);

    void delete(Integer id);
}
