package com.tts.lib.persistence.impl.generic;

import com.tts.app.configcenter.model.util.QueryFilter;
import com.tts.lib.model.generic.DataModel;
import com.tts.lib.model.generic.GenericDao;
import com.tts.lib.model.generic.GenericService;

public abstract class GenericServiceImpl<T extends DataModel, QUERY extends QueryFilter, DAO extends GenericDao<T, QUERY>> implements GenericService<T> {

    public abstract DAO getDao();

    @Override
    public T get(Long id) {
        return getDao().get(id);
    }

    
    @Override
    public void update(T object) {
        getDao().update(object);
    }

    @Override
    public void delete(Long id) {
        getDao().delete(id);
    }
}
