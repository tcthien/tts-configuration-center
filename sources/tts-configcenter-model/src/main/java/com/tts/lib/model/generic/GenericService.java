package com.tts.lib.model.generic;

public interface GenericService<T> {
    T get(Integer id);

    void update(T object);

    void delete(Integer id);
}
