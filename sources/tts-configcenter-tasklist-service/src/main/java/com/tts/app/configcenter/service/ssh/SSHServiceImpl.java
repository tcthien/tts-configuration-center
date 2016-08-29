package com.tts.app.configcenter.service.ssh;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Named;

import com.tts.app.configcenter.ssh.feature.CmdFeature;
import com.tts.app.configcenter.ssh.feature.DockerComposeFeature;
import com.tts.app.configcenter.ssh.feature.DockerFeature;

@Named(value = "sshServiceImpl")
public class SSHServiceImpl implements SSHService {
    Map<String, CmdFeature> features = new LinkedHashMap<>();

    private List<CmdFeature> getSupportedCmdFeatures() {
        return Arrays.asList((CmdFeature) new DockerFeature(), (CmdFeature) new DockerComposeFeature());
    }

    @PostConstruct
    public void init() {
        initCmdFeature();
    }

    private void initCmdFeature() {
        List<CmdFeature> cmds = getSupportedCmdFeatures();
        for (CmdFeature cmd : cmds) {
            features.put(cmd.getName(), cmd);
        }
    }

    @Override
    public boolean ping(String ipAddress) {
        return false;
    }

    @Override
    public Map<String, Boolean> checkFeatureStatus() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CmdStatus installFeature(String ipAddress, String featureName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CmdStatus uninstallFeature(String ipAddress, String featureName) {
        // TODO Auto-generated method stub
        return null;
    }
}
