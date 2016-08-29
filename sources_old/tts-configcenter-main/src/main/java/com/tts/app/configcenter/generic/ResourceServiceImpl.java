package com.tts.app.configcenter.generic;

import com.tts.lib.dao.generic.DataModel;
import com.tts.lib.dao.generic.GenericDao;

public abstract class ResourceServiceImpl<T extends DataModel> implements ResourceService<T> {

    protected GenericDao<T> dao;

    public GenericDao<T> getDao() {
        return dao;
    }

    public void setDao(GenericDao<T> dao) {
        this.dao = dao;
    }
    
    @Override
    public T get(Integer id) {
        return dao.get(id);
    }

    
    @Override
    public void update(T object) {
        dao.update(object);
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }
}
