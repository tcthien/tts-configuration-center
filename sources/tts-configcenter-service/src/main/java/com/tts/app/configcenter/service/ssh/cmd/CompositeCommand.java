package com.tts.app.configcenter.service.ssh.cmd;

import java.util.ArrayList;
import java.util.List;

public abstract class CompositeCommand implements Command {
    
    List<Command> subCommands = new ArrayList<>();

    public List<Command> getSubCommands() {
        return subCommands;
    }
}
