package com.tts.app.configcenter.service.ssh.feature;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.service.ssh.CmdStatus;
import com.tts.app.configcenter.service.ssh.SSHCommandExecutor;
import com.tts.app.configcenter.service.ssh.SSHResult;
import com.tts.app.configcenter.service.ssh.cmd.SimpleCommand;

public class DockerComposeFeature extends AbstractFeature {

    public DockerComposeFeature(SSHCommandExecutor executor) {
        super(executor);
    }

    @Override
    public String getName() {
        return "Docker Compose";
    }
    
    @Override
    public String getOSName() {
        return "ubuntu";
    }

    @Override
    public String getOSVersion() {
        return "all";
    }

    @Override
    public String getDescription() {
        return "Feature to support docker-compose command";
    }

    @Override
    public boolean check(Server server) throws Exception {
        SSHResult rs = new SimpleCommand("docker-compose --version").execute(executor, server);
        return rs.getOutputText().contains("docker-compose version");
    }

    @Override
    public CmdStatus install(Server server) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CmdStatus uninstall(Server server) {
        // TODO Auto-generated method stub
        return null;
    }
}
