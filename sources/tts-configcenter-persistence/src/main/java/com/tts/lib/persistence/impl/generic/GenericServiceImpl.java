package com.tts.lib.persistence.impl.generic;

import com.tts.lib.model.generic.DataModel;
import com.tts.lib.model.generic.GenericDao;
import com.tts.lib.model.generic.GenericService;

public abstract class GenericServiceImpl<T extends DataModel, DAO extends GenericDao<T>> implements GenericService<T> {

    public abstract DAO getDao();

    @Override
    public T get(Integer id) {
        return getDao().get(id);
    }

    
    @Override
    public void update(T object) {
        getDao().update(object);
    }

    @Override
    public void delete(Integer id) {
        getDao().delete(id);
    }
}
