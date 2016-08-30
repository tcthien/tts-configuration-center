package com.tts.lib.persistence.impl.generic;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.transaction.Transactional;

import com.tts.lib.model.generic.DataModel;
import com.tts.lib.model.generic.GenericDao;

@Transactional
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
    public T add(T obj) {
        obj = em.merge(obj);
        em.flush();
        return obj;
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public T get(Integer id) {
        return em.find(entityClass, id);
    }
    
    @Transactional(Transactional.TxType.SUPPORTS)
    public Collection<T> gets() {
        CriteriaQuery<T> query = createQuery();
        return em.createQuery(query.select(query.from(entityClass))).getResultList();
    }

    protected CriteriaQuery<T> createQuery() {
        return getCriteriaBuilder().createQuery(entityClass);
    }

    protected CriteriaBuilder getCriteriaBuilder() {
        return em.getCriteriaBuilder();
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
