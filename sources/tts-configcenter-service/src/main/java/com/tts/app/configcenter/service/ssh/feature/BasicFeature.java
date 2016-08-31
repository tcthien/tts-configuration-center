package com.tts.app.configcenter.service.ssh.feature;

import com.tts.app.configcenter.service.ssh.SSHCommandExecutor;

public abstract class BasicFeature implements SoftwareFeature {

    protected SSHCommandExecutor executor;
    
    public BasicFeature(SSHCommandExecutor executor) {
        this.executor = executor;
    }

    public SSHCommandExecutor getExecutor() {
        return executor;
    }

    public void setExecutor(SSHCommandExecutor executor) {
        this.executor = executor;
    }
}
