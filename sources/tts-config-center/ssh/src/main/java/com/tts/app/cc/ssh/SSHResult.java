package com.tts.app.cc.ssh;

public interface SSHResult {
    int getExistStatus();
    String getOutputText();
    void setExitStatus(int exitStatus);
    void setOutputText(String string);
}
