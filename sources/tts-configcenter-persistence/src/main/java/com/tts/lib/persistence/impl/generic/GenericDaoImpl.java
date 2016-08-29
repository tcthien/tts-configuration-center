package com.tts.lib.persistence.impl.generic;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.tts.lib.model.generic.DataModel;
import com.tts.lib.model.generic.GenericDao;

public abstract class GenericDaoImpl<T extends DataModel> implements GenericDao<T> {

    @PersistenceContext(unitName="configcenter")
    protected EntityManager em;
    
    protected Class<T> entityClass;
    protected String entityName;
    
    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.entityName = entityClass.getSimpleName();
    }
    
    @Override
    public void add(T obj) {
        em.persist(obj);
        em.flush();
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public T get(Integer id) {
        return em.find(entityClass, id);
    }

    @Override
    public void update(T object) {
        em.merge(object);
    }

    @Override
    public void delete(Integer id) {
        em.remove(get(id));
    }

}
