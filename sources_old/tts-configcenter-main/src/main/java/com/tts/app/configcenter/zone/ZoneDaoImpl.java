package com.tts.app.configcenter.zone;

import com.tts.lib.dao.generic.GenericDaoImpl;

public class ZoneDaoImpl extends GenericDaoImpl<Zone> implements ZoneDao {

    public ZoneDaoImpl() {
        super(Zone.class);
    }

}
