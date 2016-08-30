package com.tts.app.configcenter.service.ssh.cmd;

public abstract class AbstractCommand implements Command {

    private String command;
    private String sudoPass;
    
    public AbstractCommand() {
        this(null);
    }

    public AbstractCommand(String cmd) {
        this(cmd, null);
    }

    public AbstractCommand(String cmd, String sudoPass) {
        this.sudoPass = sudoPass;
        this.command = cmd != null ? buildTextCommand(cmd.trim()) : null;
    }

    public String getTextCommand() {
        return command;
    }

    protected String buildTextCommand(String cmd) {
        int idx = cmd.indexOf("sudo");
        if (idx >= 0 && sudoPass != null) {
            cmd = cmd.substring(idx + 1).trim();
            return "echo " + sudoPass + " | sudo -S " + cmd;
        }
        return cmd;
    }

}
