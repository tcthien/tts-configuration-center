package com.tts.app.configcenter.generic;

public interface ResourceService<T> {
    T get(Integer id);

    void update(T object);

    void delete(Integer id);
}
