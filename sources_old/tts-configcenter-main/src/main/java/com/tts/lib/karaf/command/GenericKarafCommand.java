package com.tts.lib.karaf.command;

public abstract class GenericKarafCommand<T, SERVICE> {
    protected SERVICE service;
    
    public SERVICE getService() {
        return service;
    }
    
    public void setService(SERVICE service) {
        this.service = service;
    }
    
    protected Object doExecute() throws Exception{
        return invoke();
    }
    
    protected abstract T invoke() throws Exception;
}
