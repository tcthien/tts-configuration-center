package com.tts.app.configcenter.service.ssh.cmd.docker;

import com.tts.app.configcenter.service.ssh.cmd.CompositeCommand;
import com.tts.app.configcenter.service.ssh.cmd.SimpleCommand;

public class InstallDockerComposeCommand extends CompositeCommand {

    public InstallDockerComposeCommand(String sudoPass) {
        subCommands.add(new SimpleCommand("sudo chmod 777 /usr/local/bin", sudoPass));
        subCommands.add(new SimpleCommand("sudo curl -L https://github.com/docker/compose/releases/download/1.8.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose", sudoPass));
        subCommands.add(new SimpleCommand("sudo chmod +x /usr/local/bin/docker-compose", sudoPass));
    }

}
