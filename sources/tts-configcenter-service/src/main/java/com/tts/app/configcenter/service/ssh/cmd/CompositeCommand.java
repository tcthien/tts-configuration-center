package com.tts.app.configcenter.service.ssh.cmd;

import java.util.ArrayList;
import java.util.List;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.service.ssh.SSHCommandExecutor;
import com.tts.app.configcenter.service.ssh.SSHResult;

public abstract class CompositeCommand implements Command {
    
    protected List<Command> subCommands = new ArrayList<>();

    public List<Command> getSubCommands() {
        return subCommands;
    }
    
    @Override
    public String getTextCommand() {
        return null;
    }

    @Override
    public int getTimeout() {
        return -1;
    }
    
    @Override
    public SSHResult execute(SSHCommandExecutor executor, Server server) throws Exception {
        return executor.execute(server.getIpAddress(), server.getUserName(), server.getPassword(), this);
    }
}
