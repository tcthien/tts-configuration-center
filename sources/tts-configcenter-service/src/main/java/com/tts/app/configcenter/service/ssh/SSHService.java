package com.tts.app.configcenter.service.ssh;

import java.util.List;
import java.util.Map;

import com.tts.app.configcenter.service.ssh.feature.CmdFeature;

public interface SSHService {
    boolean ping(String srIpAddress, String desIpAddress) throws Exception;
    
    List<CmdFeature> getSupportedFeatures();

    /**
     * @param ipAddress 
     * @return <CmdFeature Name, Installed/Uninstalled>
     */
    Map<CmdFeature, Boolean> checkFeatureStatus(String ipAddress) throws Exception;

    SSHResult installFeature(String ipAddress, String featureName) throws Exception;

    SSHResult uninstallFeature(String ipAddress, String featureName) throws Exception;
}
