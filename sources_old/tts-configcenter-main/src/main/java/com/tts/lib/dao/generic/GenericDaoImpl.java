package com.tts.lib.dao.generic;

import javax.persistence.EntityManager;

import com.tts.app.configcenter.generic.BeanUtil;

public abstract class GenericDaoImpl<T extends DataModel> implements GenericDao<T> {

    protected EntityManager em;
    protected Class<T> entityClass;
    protected String entityName;
    
    public GenericDaoImpl(Class<T> entityClass) {
        this.entityClass = entityClass;
        this.entityName = entityClass.getSimpleName();
    }

    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
    
    @Override
    public T get(Integer id) {
        return em.getReference(entityClass, id);
    }

    @Override
    public void update(T object) {
        T item = null;
        if (object.getId() != null) {
            item = get(object.getId());
            if (item != null) {
                BeanUtil.merge(item, object);
            }
        } else {
            item = object;
        }
        em.persist(item);
        em.flush();
    }

    @Override
    public void delete(Integer id) {
        T item = get(id);
        em.remove(item);
        em.flush();
    }

}
