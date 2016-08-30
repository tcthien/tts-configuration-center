package com.tts.app.configcenter.service.ssh.feature;

import com.tts.app.configcenter.service.ssh.SSHCommandExecutor;

public abstract class AbstractFeature implements CmdFeature {

    protected SSHCommandExecutor executor;
    
    public AbstractFeature(SSHCommandExecutor executor) {
        this.executor = executor;
    }

    public SSHCommandExecutor getCommandExecutor() {
        return executor;
    }

    public void setCommandExecutor(SSHCommandExecutor commandExecutor) {
        this.executor = commandExecutor;
    }
}
