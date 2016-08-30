package com.tts.app.configcenter.service.ssh.feature;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.service.ssh.SSHCommandExecutor;
import com.tts.app.configcenter.service.ssh.SSHResult;
import com.tts.app.configcenter.service.ssh.cmd.SimpleCommand;

public class DockerFeature extends AbstractFeature {

    public DockerFeature(SSHCommandExecutor executor) {
        super(executor);
    }

    @Override
    public String getName() {
        return "Docker";
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
        return "Feature to support docker command";
    }

    @Override
    public boolean check(Server server) throws Exception {
        SSHResult rs = new SimpleCommand("docker --version").execute(executor, server);
        return rs.getOutputText().contains("Docker version");
    }

    @Override
    public SSHResult install(Server server) throws Exception {
        return null;
    }

    @Override
    public SSHResult uninstall(Server server) throws Exception {
        return null;
    }

}
