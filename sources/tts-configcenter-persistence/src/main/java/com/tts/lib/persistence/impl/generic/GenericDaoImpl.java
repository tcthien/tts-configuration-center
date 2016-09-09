package com.tts.lib.persistence.impl.generic;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.tts.app.configcenter.model.util.QueryFilter;
import com.tts.lib.model.generic.DataModel;
import com.tts.lib.model.generic.GenericDao;

@Transactional
public abstract class GenericDaoImpl<T extends DataModel, QUERY extends QueryFilter> implements GenericDao<T, QUERY> {

    @PersistenceContext(unitName="configcenter")
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
    public T add(T obj) {
        obj = em.merge(obj);
        em.flush();
        return obj;
    }

    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public T get(Long id) {
        return em.find(entityClass, id);
    }
    
    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public Collection<T> gets() {
        CriteriaBuilder builder = getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(entityClass);
        return em.createQuery(query.select(query.from(entityClass))).getResultList();
    }
    
    @Override
    @Transactional(Transactional.TxType.SUPPORTS)
    public List<T> findByQuery(QUERY myFilter) {
        CriteriaBuilder builder = getCriteriaBuilder();

        CriteriaQuery<T> criteria = builder.createQuery(entityClass);
        Root<T> rootQuery = criteria.from(entityClass);
        
        // Decorate more restriction
        List<Predicate> andQueries = decorate(builder, rootQuery, myFilter);
        if (!andQueries.isEmpty()) {
            criteria = criteria.select(rootQuery);
            criteria.where(builder.and(andQueries.toArray(new Predicate[andQueries.size()])));
        }
        
        return em.createQuery(criteria).getResultList();
    }

    protected List<Predicate> decorate(CriteriaBuilder builder, Root<T> rootQuery, QUERY myFilter) {
        return Collections.emptyList();
    }

    protected CriteriaBuilder getCriteriaBuilder() {
        return em.getCriteriaBuilder();
    }

    @Override
    public void update(T object) {
        em.merge(object);
    }

    @Override
    public void delete(Long id) {
        em.remove(get(id));
    }
}
