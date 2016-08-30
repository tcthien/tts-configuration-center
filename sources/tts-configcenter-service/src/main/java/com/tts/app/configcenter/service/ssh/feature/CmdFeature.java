package com.tts.app.configcenter.service.ssh.feature;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.service.ssh.SSHResult;

public interface CmdFeature {
    String getName();
    String getDescription();
    String getOSName();
    String getOSVersion();
    boolean check(Server server) throws Exception;
    SSHResult install(Server server) throws Exception;
    SSHResult uninstall(Server server) throws Exception;
}
