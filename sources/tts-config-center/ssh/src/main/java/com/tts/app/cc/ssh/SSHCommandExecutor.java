package com.tts.app.cc.ssh;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.jcraft.jsch.JSchException;

public interface SSHCommandExecutor {
    SSHResult execute(String host, String userName, String password, SSHCommand cmd) throws JSchException, IOException;
    Map<SSHCommand, SSHResult> execute(String host, String userName, String password, List<SSHCommand> cmds) throws JSchException, IOException;
}
