package com.tts.app.configcenter.service.ssh;

import javax.jws.WebService;

import com.tts.app.configcenter.service.ssh.cmd.Command;

@WebService
public interface SSHCommandExecutor {
    SSHResult execute(String host, String userName, String password, Command cmds) throws Exception;

    SSHResult execute(String host, String userName, String password, Command cmd, SSHCommandCallback callback) throws Exception;
}
