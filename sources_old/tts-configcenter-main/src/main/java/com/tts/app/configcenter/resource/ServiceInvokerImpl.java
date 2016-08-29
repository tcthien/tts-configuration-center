package com.tts.app.configcenter.resource;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.tts.app.configcenter.generic.ResourceService;
import com.tts.app.configcenter.server.ServerServiceImpl;
import com.tts.app.configcenter.zone.ZoneServiceImpl;

public class ServiceInvokerImpl implements ServiceInvoker {
    Map<String, ResourceService<?>> serviceHandlers = new HashMap<String, ResourceService<?>>();
    
    Set<String> idMethods = new HashSet<>(Arrays.asList("get", "delete", "load", "findById"));
    Set<String> updateMethods = new HashSet<>(Arrays.asList("save", "put", "update"));

    public void init() {
        serviceHandlers.put("zone", new ZoneServiceImpl());
        serviceHandlers.put("server", new ServerServiceImpl());
    }

    @Override
    public Object invoke(String resource, String operation, Object value) throws Exception {
        ResourceService<?> handler = serviceHandlers.get(resource);
        if (handler == null) {
            throw new UnsupportedOperationException("Resource " + resource + " isn't supported");
        }
        
        if (idMethods.contains(operation)) {
            Method method = handler.getClass().getMethod(operation, Integer.class);
            return method.invoke(handler, Integer.parseInt(value.toString()));
        }
        
        if (updateMethods.contains(operation)) {
            Method method = handler.getClass().getMethod(operation, value.getClass());
            return method.invoke(handler, value);
        }
        throw new UnsupportedOperationException("Resource " + resource + " isn't supported");
    }
}
