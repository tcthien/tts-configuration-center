package com.tts.app.configcenter.model.server;

import javax.jws.WebService;

import com.tts.lib.model.generic.GenericDao;

@WebService
public interface ServerDao extends GenericDao<Server, ServerQueryFilter> {
    Server findByServerIP(String ipAddress);
}
