package com.tts.app.configcenter.handler;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ServiceInvokerImpl implements ServiceInvoker {
    Map<String, ServiceHandler<?>> serviceHandlers = new HashMap<String, ServiceHandler<?>>();
    
    Set<String> idMethods = new HashSet<>(Arrays.asList("get", "delete", "load", "findById"));
    Set<String> updateMethods = new HashSet<>(Arrays.asList("save", "put", "update"));

    public void init() {
        serviceHandlers.put("zone", new ZoneHandler());
        serviceHandlers.put("server", new ServerHandler());
    }

    @Override
    public Object invoke(String resource, String operation, Object value) throws Exception {
        ServiceHandler<?> handler = serviceHandlers.get(resource);
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
