package com.tts.app.configcenter.service.ssh.cmd;

public abstract class AbstractCommand implements Command {

    private String command;
    private String sudoPass;
    
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
        boolean isSudoCmd = cmd.trim().startsWith("sudo");
        if (isSudoCmd && sudoPass != null) {
            cmd = cmd.substring(4).trim();
            return "echo " + sudoPass + " | sudo -S " + cmd;
        }
        return cmd;
    }
    
    @Override
    public int getTimeout() {
        return -1;
    }

}
