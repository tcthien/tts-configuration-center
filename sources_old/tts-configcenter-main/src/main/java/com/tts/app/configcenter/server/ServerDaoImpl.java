package com.tts.app.configcenter.server;

import com.tts.lib.dao.generic.GenericDaoImpl;

public class ServerDaoImpl extends GenericDaoImpl<Server> implements ServerDao {

    public ServerDaoImpl() {
        super(Server.class);
    }

}
