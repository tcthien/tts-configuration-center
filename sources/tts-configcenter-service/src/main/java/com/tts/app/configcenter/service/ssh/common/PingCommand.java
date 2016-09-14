package com.tts.app.configcenter.service.ssh.common;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.service.ssh.SSHCommandExecutor;
import com.tts.app.configcenter.service.ssh.SSHResult;
import com.tts.app.configcenter.service.ssh.cmd.SimpleCommand;

public class PingCommand extends SimpleCommand {
    
    public PingCommand(String serverIp) {
        super("ping " + serverIp);
    }

    @Override
    public SSHResult execute(SSHCommandExecutor executor, Server server) throws Exception {
        SSHResult rs = super.execute(executor, server);
        String txt = rs.getOutputText();
        if (txt.contains("icmp_seq") || txt.contains("TTL") || txt.contains("ttl")) {
            rs.setExitStatus(SSHResult.PING_REACHABLE);
        } else {
            rs.setExitStatus(SSHResult.PING_UNREACHABLE);
        }
        return rs;
    }
    
    @Override
    public int getTimeout() {
        return 2;
    }
}
