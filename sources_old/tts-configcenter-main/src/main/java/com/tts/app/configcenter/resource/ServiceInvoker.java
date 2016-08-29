package com.tts.app.configcenter.resource;

public interface ServiceInvoker {
    Object invoke(String resource, String operation, Object id) throws Exception;
}
