package com.tts.app.configcenter.service.ssh.cmd;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.service.ssh.SSHCommandExecutor;
import com.tts.app.configcenter.service.ssh.SSHResult;

public class SimpleCommand extends AbstractCommand {

    public SimpleCommand(String cmd) {
        super(cmd);
    }

    @Override
    public SSHResult execute(SSHCommandExecutor executor, Server server) throws Exception {
        return executor.execute(server.getIpAddress(), server.getUserName(), server.getPassword(), this);
    }

}
