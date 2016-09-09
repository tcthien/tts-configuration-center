package com.tts.lib.model.generic;

public interface GenericService<T> {
    T get(Long id);

    void update(T object);

    void delete(Long id);
}
