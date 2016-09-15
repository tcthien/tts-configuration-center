package com.tts.app.configcenter.service.ssh;

public interface SSHConsoleLog {

    void log(String ipAddress, String outputConsole);
    
    String getLog(String ipAddress);

}
