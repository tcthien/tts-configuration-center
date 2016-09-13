package com.tts.app.configcenter.service.ssh.feature;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.model.ssh.SSHFeature;
import com.tts.app.configcenter.service.ssh.SSHCommandExecutor;
import com.tts.app.configcenter.service.ssh.SSHResult;
import com.tts.app.configcenter.service.ssh.SSHResultImpl;
import com.tts.app.configcenter.service.ssh.cmd.CompositeCommand;
import com.tts.app.configcenter.service.ssh.cmd.SimpleCommand;

public class TMASmartOfficeFeature extends BasicFeature {

    public TMASmartOfficeFeature(SSHCommandExecutor executor) {
        super(executor);
        addDependency(new DockerFeature(executor));
    }

    @Override
    public SSHFeature getFeatureInfo() {
        return SSHFeature.create().name("TMA Smart Office").osName("ubuntu").osVersion("all").description("Feature to support TMA Smart Office");
    }
    
    @Override
    public boolean check(Server server) throws Exception {
        SSHResult rs = new SimpleCommand("~/tma-so-software/version.sh").execute(executor, server);
        return rs.getOutputText().contains("TMA Smart Office");
    }
    
    static class InstallTMASmartOfficeCommand extends CompositeCommand {

        public InstallTMASmartOfficeCommand(String sudoPass) {
            subCommands.add(new SimpleCommand("mkdir ~/tmp"));
            subCommands.add(new SimpleCommand("mkdir ~/tmp/tma-so"));
            subCommands.add(new SimpleCommand("chmod 777 ~/tmp/tma-so"));
            subCommands.add(new SimpleCommand("curl -L https://raw.githubusercontent.com/tcthien/tma-so/master/install.sh > ~/tmp/tma-so/install.sh"));
            subCommands.add(new SimpleCommand("chmod +x ~/tmp/tma-so/install.sh"));
            subCommands.add(new SimpleCommand("~/tmp/tma-so/install.sh ubuntu"));
        }

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
        new SimpleCommand("~/tma-so-software/sources/uninstall.sh", server.getPassword()).execute(executor, server);
        boolean status = check(server);
        SSHResult rs = new SSHResultImpl();
        // After uninstalling, if status is true => uninstall NOK
        rs.setExitStatus(status ? SSHResult.STATUS_NOK : SSHResult.STATUS_OK);
        return rs;
    }
}
