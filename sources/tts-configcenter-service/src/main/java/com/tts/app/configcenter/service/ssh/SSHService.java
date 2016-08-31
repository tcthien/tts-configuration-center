package com.tts.app.configcenter.service.ssh;

import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import com.tts.app.configcenter.model.ssh.SSHFeature;
import com.tts.app.configcenter.service.ssh.feature.SoftwareFeature;

@WebService
public interface SSHService {
    boolean ping(String srIpAddress, String desIpAddress) throws Exception;
    
    List<SoftwareFeature> getSupportedFeatures();

    /**
     * @param ipAddress 
     * @return <CmdFeature Name, Installed/Uninstalled>
     */
    Map<SoftwareFeature, Boolean> checkFeatureStatus(String ipAddress) throws Exception;

    SSHResult installFeature(String ipAddress, SSHFeature feature) throws Exception;

    SSHResult uninstallFeature(String ipAddress, SSHFeature feature, boolean removeDependency) throws Exception;

    void addFeature(SoftwareFeature feature);
}
