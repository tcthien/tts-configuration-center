package com.tts.app.configcenter.service.ssh.cmd.docker;

import com.tts.app.configcenter.service.ssh.cmd.CompositeCommand;
import com.tts.app.configcenter.service.ssh.cmd.SimpleCommand;

public class InstallDockerCommand extends CompositeCommand {

    public InstallDockerCommand(String sudoPass) {
        subCommands.add(new SimpleCommand("mkdir ~/tmp"));
        subCommands.add(new SimpleCommand("mkdir ~/tmp/tts-configcenter"));
        subCommands.add(new SimpleCommand("curl -L https://get.docker.com/ > ~/tmp/tts-configcenter/installDocker.sh"));
        subCommands.add(new SimpleCommand("sudo sh ~/tmp/tts-configcenter/installDocker.sh", sudoPass));
    }

}
