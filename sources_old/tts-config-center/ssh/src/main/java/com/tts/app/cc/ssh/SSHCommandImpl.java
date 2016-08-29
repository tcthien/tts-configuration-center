package com.tts.app.cc.ssh;

public class SSHCommandImpl implements SSHCommand {

    private String command;
    private String sudoPass;
    
    public static SSHCommand createSudoCommand(String cmd, String sudoPass) {
        return new SSHCommandImpl(cmd, sudoPass);
    }
    
    public static SSHCommand createNonSudoCommand(String cmd) {
        return new SSHCommandImpl(cmd);
    }

    private SSHCommandImpl(String cmd) {
        this(cmd, null);
    }

    private SSHCommandImpl(String cmd, String sudoPass) {
        this.sudoPass = sudoPass;
        this.command = buildTextCommand(cmd.trim());
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
