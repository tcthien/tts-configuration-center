package com.tts.app.configcenter.service.ssh.feature;

import java.util.List;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.model.ssh.SSHFeature;
import com.tts.app.configcenter.service.ssh.SSHResult;

public interface SoftwareFeature {
    SSHFeature getFeatureInfo();
    boolean check(Server server) throws Exception;
    SSHResult install(Server server) throws Exception;
    SSHResult uninstall(Server server, boolean removeDependency) throws Exception;
    List<SoftwareFeature> getDependencies();
}
