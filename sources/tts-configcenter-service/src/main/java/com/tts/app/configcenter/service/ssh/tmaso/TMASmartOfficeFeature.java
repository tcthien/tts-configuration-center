package com.tts.app.configcenter.service.ssh.tmaso;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.model.ssh.SSHFeature;
import com.tts.app.configcenter.service.ssh.SSHCommandExecutor;
import com.tts.app.configcenter.service.ssh.SSHResult;
import com.tts.app.configcenter.service.ssh.SSHResultImpl;
import com.tts.app.configcenter.service.ssh.cmd.SimpleCommand;
import com.tts.app.configcenter.service.ssh.common.BasicFeature;
import com.tts.app.configcenter.service.ssh.docker.DockerComposeFeature;
import com.tts.app.configcenter.service.ssh.docker.DockerFeature;

public class TMASmartOfficeFeature extends BasicFeature {

    public TMASmartOfficeFeature(SSHCommandExecutor executor) {
        super(executor);
        addDependency(new DockerFeature(executor));
        addDependency(new DockerComposeFeature(executor));
        addUICommands();
    }
    
    private void addUICommands() {
        String scriptFolder = "~/tma-so-software/sources/scripts/bin";
        // MQTT Server commands
        addUICommand(new SimpleCommand(scriptFolder + "/run start u mqtt", null, "TMA SO - Start MQTT", "Used to start MQTT Server"));
        addUICommand(new SimpleCommand(scriptFolder + "/run stop u mqtt", null, "TMA SO - Stop MQTT", "Used to stop MQTT Server"));
        // OpenHAB Server commands
        addUICommand(new SimpleCommand(scriptFolder + "/run start u openhab2", null, "TMA SO - Start OpenHAB2", "Used to start OpenHAB2 Server"));
        addUICommand(new SimpleCommand(scriptFolder + "/run stop u openhab2", null, "TMA SO - Stop  OpenHAB2", "Used to stop OpenHAB2 Server"));
    }

    @Override
    public SSHFeature getFeatureInfo() {
        return SSHFeature.create().name("TMA Smart Office").osName("ubuntu").osVersion("all").description("Feature to support TMA Smart Office");
    }
    
    @Override
    public boolean check(Server server) throws Exception {
        SSHResult rs = new SimpleCommand("~/tma-so-software/sources/version.sh").execute(executor, server);
        return rs.getOutputText().contains("TMA Smart Office");
    }
    
    @Override
    protected SSHResult installComponent(Server server) throws Exception {
        new InstallTMASmartOfficeCommand(server.getPassword()).execute(executor, server);
        boolean status = check(server);
        SSHResult rs = new SSHResultImpl();
        // After installing, if status is true => install OK
        rs.setExitStatus(status ? SSHResult.STATUS_OK : SSHResult.STATUS_NOK);
        return rs;
    }

    @Override
    protected SSHResult uninstallComponent(Server server) throws Exception {
        new SimpleCommand("sudo rm -rf ~/tma-so-software/", server.getPassword()).execute(executor, server);
        new SimpleCommand("sudo rm -rf ~/tmp/tma-so/", server.getPassword()).execute(executor, server);
        boolean status = check(server);
        SSHResult rs = new SSHResultImpl();
        // After uninstalling, if status is true => uninstall NOK
        rs.setExitStatus(status ? SSHResult.STATUS_NOK : SSHResult.STATUS_OK);
        return rs;
    }
}
