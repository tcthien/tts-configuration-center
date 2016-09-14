package com.tts.app.configcenter.service.ssh.tmaso;

import com.tts.app.configcenter.service.ssh.cmd.CompositeCommand;
import com.tts.app.configcenter.service.ssh.cmd.SimpleCommand;

public class InstallTMASmartOfficeCommand extends CompositeCommand {

    public InstallTMASmartOfficeCommand(String sudoPass) {
        subCommands.add(new SimpleCommand("mkdir ~/tmp"));
        subCommands.add(new SimpleCommand("mkdir ~/tmp/tma-so"));
        subCommands.add(new SimpleCommand("chmod 777 ~/tmp/tma-so"));
        subCommands.add(new SimpleCommand("curl -L https://raw.githubusercontent.com/tcthien/tma-so/master/install.sh > ~/tmp/tma-so/install.sh"));
        subCommands.add(new SimpleCommand("chmod +x ~/tmp/tma-so/install.sh"));
        subCommands.add(new SimpleCommand("~/tmp/tma-so/install.sh ubuntu"));
    }

}
