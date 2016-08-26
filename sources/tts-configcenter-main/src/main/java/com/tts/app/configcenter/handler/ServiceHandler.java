package com.tts.app.configcenter.handler;

public interface ServiceHandler<T> {
    T get(Integer id);

    void update(T object);

    void delete(Integer id);
}
