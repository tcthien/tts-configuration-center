package com.tts.app.configcenter.service.ssh;

public class SSHResultImpl implements SSHResult {

    private int exitStatus;
    private String text;
    
    public SSHResultImpl() {
    }
    
    public SSHResultImpl(int exitStatus, String text) {
        this.exitStatus = exitStatus;
        this.text = text;
    }

    public int getExistStatus() {
        return exitStatus;
    }

    public String getOutputText() {
        return text;
    }

    public void setExitStatus(int exitStatus) {
        this.exitStatus = exitStatus;
    }

    public void setOutputText(String text) {
        this.text = text;
    }

}
