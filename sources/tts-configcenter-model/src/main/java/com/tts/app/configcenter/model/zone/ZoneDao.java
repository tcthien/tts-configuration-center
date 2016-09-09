package com.tts.app.configcenter.model.zone;

import javax.jws.WebService;

import com.tts.lib.model.generic.GenericDao;

@WebService
public interface ZoneDao extends GenericDao<Zone, ZoneQueryFilter> {
}
