package com.tts.app.configcenter.service.ssh;

public interface SSHResult {
    static int STATUS_OK = 1;
    static int STATUS_NOK = 0;
    
    static int PING_UNREACHABLE = 0;
    static int PING_REACHABLE = 1;
    
    int getExistStatus();
    String getOutputText();
    void setExitStatus(int exitStatus);
    void setOutputText(String string);
}
