package com.tts.app.configcenter.service.ssh.feature;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.service.ssh.SSHCommandExecutor;
import com.tts.app.configcenter.service.ssh.SSHResult;
import com.tts.app.configcenter.service.ssh.SSHResultImpl;
import com.tts.app.configcenter.service.ssh.cmd.InstallDockerComposeCommand;
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
    public SSHResult install(Server server) throws Exception {
        new InstallDockerComposeCommand(server.getPassword()).execute(executor, server);
        boolean status = check(server);
        SSHResult rs = new SSHResultImpl();
        // After installing, if status is true => install OK
        rs.setExitStatus(status ? SSHResult.STATUS_OK : SSHResult.STATUS_NOK);
        return rs;
    }

    @Override
    public SSHResult uninstall(Server server) throws Exception {
        new SimpleCommand("sudo rm /usr/local/bin/docker-compose", server.getPassword()).execute(executor, server);
        boolean status = check(server);
        SSHResult rs = new SSHResultImpl();
        // After uninstalling, if status is true => uninstall NOK
        rs.setExitStatus(status ? SSHResult.STATUS_NOK : SSHResult.STATUS_OK);
        return rs;
    }
}
