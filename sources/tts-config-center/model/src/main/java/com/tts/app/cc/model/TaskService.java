package com.tts.app.cc.model;

import java.util.Collection;

import javax.jws.WebService;

@WebService
public interface TaskService {
    void add(Task task);

    void update(Task task);

    void delete(Integer id);

    Task find(Integer i);

    Collection<Task> find();
}
