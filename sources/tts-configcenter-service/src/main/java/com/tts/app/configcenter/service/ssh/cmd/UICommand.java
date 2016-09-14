package com.tts.app.configcenter.service.ssh.cmd;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.service.ssh.SSHCommandExecutor;
import com.tts.app.configcenter.service.ssh.SSHResult;

public interface UICommand {
    String getName();

    String getDescription();

    SSHResult execute(SSHCommandExecutor executor, Server server) throws Exception;
}
