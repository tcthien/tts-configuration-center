package com.tts.app.configcenter.service.ssh;

import com.tts.app.configcenter.service.ssh.cmd.Command;

public interface SSHCommandCallback {

    boolean preExecute(Command sshCommand);

    boolean postExecute(Command sshCommand);

}
