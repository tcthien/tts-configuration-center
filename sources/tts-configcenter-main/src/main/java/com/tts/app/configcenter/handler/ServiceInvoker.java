package com.tts.app.configcenter.handler;

public interface ServiceInvoker {
    Object invoke(String resource, String operation, Object id) throws Exception;
}
