package com.tts.app.configcenter.service.ssh.feature;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.service.ssh.CmdStatus;

public interface CmdFeature {
    String getName();
    String getDescription();
    String getOSName();
    String getOSVersion();
    boolean check(Server server) throws Exception;
    CmdStatus install(Server server) throws Exception;
    CmdStatus uninstall(Server server) throws Exception;
}
