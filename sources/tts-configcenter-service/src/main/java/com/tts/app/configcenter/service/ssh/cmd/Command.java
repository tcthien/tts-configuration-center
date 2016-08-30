package com.tts.app.configcenter.service.ssh.cmd;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.service.ssh.SSHCommandExecutor;
import com.tts.app.configcenter.service.ssh.SSHResult;

public interface Command {
    String getTextCommand();
    
    int getTimeout();
    
    /**
     * Execute command on specific server
     * @param executor
     * @param server
     * @return
     */
    SSHResult execute(SSHCommandExecutor executor, Server server) throws Exception;
}
