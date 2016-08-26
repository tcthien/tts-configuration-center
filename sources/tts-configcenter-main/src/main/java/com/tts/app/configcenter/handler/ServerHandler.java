package com.tts.app.configcenter.handler;

import com.tts.app.configcenter.model.Server;

public class ServerHandler extends GenericHandler<Server> {

    @Override
    public Server get(Integer id) {
        Server obj = new Server();
        obj.setId(0);
        obj.setIpAddress("192.168.1.1");
        obj.setName("tcthien-pc");
        obj.setPassword("tcthien");
        obj.setUserName("tcthien");
        return obj;
    }

    
    @Override
    public void update(Server object) {
        
    }

    @Override
    public void delete(Integer id) {
        
    }

}
