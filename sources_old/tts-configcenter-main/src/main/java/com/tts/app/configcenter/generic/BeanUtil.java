package com.tts.app.configcenter.generic;

import com.tts.app.configcenter.server.Server;
import com.tts.app.configcenter.zone.Zone;

public class BeanUtil {

    public static void mergeZone(Zone root, Zone data) {
        root.setName(data.getName());
    }
    
    public static void mergeServer(Server root, Server data) {
        root.setIpAddress(data.getIpAddress());
        root.setName(data.getName());
        root.setPassword(data.getPassword());
        root.setUserName(data.getUserName());
    }

    public static <T> void merge(T root, T data) {
        // FIXME: Specific implementation temporarily
        if (root instanceof Zone) {
            mergeZone((Zone) root, (Zone) data);
        } else if (root instanceof Server) {
            mergeServer((Server) root, (Server) data);
        } 
    }

}
