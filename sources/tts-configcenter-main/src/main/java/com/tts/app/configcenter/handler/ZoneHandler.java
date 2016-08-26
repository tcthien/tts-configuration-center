package com.tts.app.configcenter.handler;

import com.tts.app.configcenter.Zone;

public class ZoneHandler extends GenericHandler<Zone> {

    Zone obj = new Zone();
    
    public ZoneHandler() {
        obj.setId(0);
        obj.setName("Tempo");
    }
    
    @Override
    public Zone get(Integer id) {
        return obj;
    }

    
    @Override
    public void update(Zone object) {
        obj.setId(object.getId());
        obj.setName(object.getName());
    }

    @Override
    public void delete(Integer id) {
        
    }

}
