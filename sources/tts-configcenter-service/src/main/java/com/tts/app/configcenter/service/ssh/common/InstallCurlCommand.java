package com.tts.app.configcenter.service.ssh.common;

import com.tts.app.configcenter.service.ssh.cmd.CompositeCommand;
import com.tts.app.configcenter.service.ssh.cmd.SimpleCommand;

public class InstallCurlCommand extends CompositeCommand {

    public InstallCurlCommand(String sudoPass) {
        subCommands.add(new SimpleCommand("sudo apt-get update", sudoPass));
        subCommands.add(new SimpleCommand("sudo apt-get install curl", sudoPass));
    }

}
