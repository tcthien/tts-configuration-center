package com.tts.app.configcenter.service.ssh;

import java.util.Map;

public interface SSHService {
    boolean ping(String ipAddress);

    /**
     * @return <CmdFeature Name, Installed/Uninstalled>
     */
    Map<String, Boolean> checkFeatureStatus();

    CmdStatus installFeature(String ipAddress, String featureName);

    CmdStatus uninstallFeature(String ipAddress, String featureName);
}
