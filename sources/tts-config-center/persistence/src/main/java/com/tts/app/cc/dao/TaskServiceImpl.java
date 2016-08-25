package com.tts.app.cc.dao;

import java.util.Arrays;
import java.util.Collection;

import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import org.ops4j.pax.cdi.api.OsgiServiceProvider;
import org.ops4j.pax.cdi.api.Properties;
import org.ops4j.pax.cdi.api.Property;

import com.tts.app.cc.model.Task;
import com.tts.app.cc.model.TaskService;

@OsgiServiceProvider(classes = {TaskService.class})
// The properties below allow to transparently export the service as a web service using Distributed OSGi
@Properties({
    @Property(name = "service.exported.interfaces", value = "*")
})
@Named
@Transactional
public class TaskServiceImpl implements TaskService {
    
    @PersistenceContext(unitName="tasklist")
    EntityManager em;

    @Override
    public void add(Task task) {
        em.persist(task);
        em.flush();
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public Collection<Task> getTasks() {
        CriteriaQuery<Task> query = em.getCriteriaBuilder().createQuery(Task.class);
        return em.createQuery(query.select(query.from(Task.class))).getResultList();
    }

    @Override
    public void update(Task task) {
        em.merge(task);
    }
    
    @Override
    public void delete(Integer id) {
        em.remove(find(id));
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Task find(Integer id) {
        return em.find(Task.class, id);
    }

    @Override
    public Collection<Task> find() {
        return Arrays.asList((Task)find(null));
    }
}
