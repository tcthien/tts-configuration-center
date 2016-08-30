package com.tts.app.configcenter.service.ssh.feature;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.tts.app.configcenter.service.ssh.SSHCommandExecutor;
import com.tts.app.configcenter.service.ssh.SSHService;

public abstract class BasicFeature implements SoftwareFeature {

    @Inject
    SSHCommandExecutor executor;

    @Inject
    SSHService sshService;
    
    @PostConstruct
    public void init() {
        sshService.addFeature(this);
    }

    public SSHCommandExecutor getCommandExecutor() {
        return executor;
    }

    public void setCommandExecutor(SSHCommandExecutor commandExecutor) {
        this.executor = commandExecutor;
    }

    public SSHService getSSHService() {
        return sshService;
    }

    public void setSSHService(SSHService sshService) {
        this.sshService = sshService;
    }
}
