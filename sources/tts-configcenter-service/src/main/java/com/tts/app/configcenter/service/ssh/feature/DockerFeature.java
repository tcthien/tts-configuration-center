package com.tts.app.configcenter.service.ssh.feature;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.model.ssh.SSHFeature;
import com.tts.app.configcenter.service.ssh.SSHCommandExecutor;
import com.tts.app.configcenter.service.ssh.SSHResult;
import com.tts.app.configcenter.service.ssh.SSHResultImpl;
import com.tts.app.configcenter.service.ssh.cmd.SimpleCommand;
import com.tts.app.configcenter.service.ssh.cmd.docker.InstallDockerCommand;

public class DockerFeature extends BasicFeature {

    public DockerFeature(SSHCommandExecutor executor) {
        super(executor);
    }

    @Override
    public SSHFeature getFeatureInfo() {
        return SSHFeature.create().name("Docker").osName("ubuntu").osVersion("all").description("Feature to support docker command");
    }

    @Override
    public boolean check(Server server) throws Exception {
        SSHResult rs = new SimpleCommand("docker --version").execute(getExecutor(), server);
        return rs.getOutputText().contains("Docker version");
    }

    @Override
    public SSHResult install(Server server) throws Exception {
        new InstallDockerCommand(server.getPassword()).execute(getExecutor(), server);
        boolean status = check(server);
        SSHResult rs = new SSHResultImpl();
        // After installing, if status is true => install OK
        rs.setExitStatus(status ? SSHResult.STATUS_OK : SSHResult.STATUS_NOK);
        return rs;
    }

    @Override
    public SSHResult uninstall(Server server) throws Exception {
        new SimpleCommand("sudo apt-get purge -y docker-engine", server.getPassword()).execute(getExecutor(), server);
        boolean status = check(server);
        SSHResult rs = new SSHResultImpl();
        // After uninstalling, if status is true => uninstall NOK
        rs.setExitStatus(status ? SSHResult.STATUS_NOK : SSHResult.STATUS_OK);
        return rs;
    }

}
