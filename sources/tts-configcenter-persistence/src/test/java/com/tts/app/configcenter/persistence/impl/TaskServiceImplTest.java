package com.tts.app.configcenter.persistence.impl;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.Test;

import com.tts.app.configcenter.model.Task;

public class TaskServiceImplTest {

    @Test
    public void testWriteRead() throws Exception {
        TaskServiceImpl taskService = new TaskServiceImpl();
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("configcenter", System.getProperties());
        EntityManager em = emf.createEntityManager();
        taskService.em = em;

        em.getTransaction().begin();
        Task task = new Task();
        task.setId(1L);
        task.setTitle("Test task");
        taskService.addTask(task);
        em.getTransaction().commit();
        Collection<Task> persons = taskService.getTasks();

        Assert.assertEquals(1, persons.size());
        Task task1 = persons.iterator().next();
        Assert.assertEquals(new Long(1), task1.getId());
        Assert.assertEquals("Test task", task1.getTitle());
    }

}
