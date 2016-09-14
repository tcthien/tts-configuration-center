package com.tts.app.configcenter.service.ssh.common;

import java.util.List;
import java.util.Map;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.model.ssh.SSHFeature;
import com.tts.app.configcenter.service.ssh.SSHResult;
import com.tts.app.configcenter.service.ssh.cmd.UICommand;

public interface SoftwareFeature {
    SSHFeature getFeatureInfo();
    boolean check(Server server) throws Exception;
    SSHResult install(Server server) throws Exception;
    SSHResult uninstall(Server server, boolean removeDependency) throws Exception;
    List<SoftwareFeature> getDependencies();
    Map<String, UICommand> getUICommands();
}
