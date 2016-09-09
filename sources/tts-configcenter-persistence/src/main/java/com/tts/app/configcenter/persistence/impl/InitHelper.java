package com.tts.app.configcenter.persistence.impl;

import java.util.concurrent.Executors;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tts.app.configcenter.model.Task;
import com.tts.app.configcenter.model.TaskService;
import com.tts.app.configcenter.model.zone.Zone;
import com.tts.app.configcenter.model.zone.ZoneDao;

@Named
public class InitHelper {
    Logger LOG = LoggerFactory.getLogger(InitHelper.class);
    @Inject
    TaskService taskService;

    @Inject
    ZoneDao zoneDao;

    @PostConstruct
    public void addDemoTasks() {
        Executors.newSingleThreadExecutor().execute(new Runnable() {
            @Override
            public void run() {
                try {
                    if (taskService.getTask(1) == null) {
                        addSampleTask();
                    }

                    if (zoneDao.get(1L) == null) {
                        addSampleZone();
                    }

                } catch (Exception e) {
                    LOG.warn(e.getMessage(), e);
                }
            }
        });

    }

    private void addSampleTask() {
        Task task = new Task(1L, "Just a sample task", "Some more info");
        taskService.addTask(task);
    }

    private void addSampleZone() {
        Zone zone = new Zone(1L, "Test Zone");
        zoneDao.add(zone);
    }

}
