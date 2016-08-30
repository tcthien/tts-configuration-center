package com.tts.app.configcenter.service.ssh;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.ops4j.pax.cdi.api.OsgiService;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.model.server.ServerDao;
import com.tts.app.configcenter.service.ssh.cmd.common.PingCommand;
import com.tts.app.configcenter.service.ssh.feature.CmdFeature;
import com.tts.app.configcenter.service.ssh.feature.DockerComposeFeature;
import com.tts.app.configcenter.service.ssh.feature.DockerFeature;

@Named(value = "sshServiceImpl")
public class SSHServiceImpl implements SSHService {
    
    @OsgiService @Inject
    ServerDao serverDao;
    
    @Inject
    SSHCommandExecutor commandExecutor;
    
    
    List<CmdFeature> supportedFeatures; 
    
    Map<String, CmdFeature> features = new LinkedHashMap<>();

    @PostConstruct
    public void init() {
        CmdFeature docker = (CmdFeature) new DockerFeature(commandExecutor);
        CmdFeature dockerCompose = (CmdFeature) new DockerComposeFeature(commandExecutor);
        supportedFeatures = Arrays.asList(docker, dockerCompose);
        
        List<CmdFeature> cmds = getSupportedFeatures();
        for (CmdFeature cmd : cmds) {
            features.put(cmd.getName(), cmd);
        }
    }
    
    @Override
    public List<CmdFeature> getSupportedFeatures() {
        return supportedFeatures;
    }

    @Override
    public boolean ping(String srIpAddress, String desIpAddress) throws Exception {
        Server server = serverDao.findByServerIP(srIpAddress);
        SSHResult rs = new PingCommand(desIpAddress).execute(commandExecutor, server);
        return rs.getExistStatus() == SSHResult.PING_REACHABLE;
    }

    @Override
    public Map<CmdFeature, Boolean> checkFeatureStatus(String ipAddress) throws Exception {
        Server server = serverDao.findByServerIP(ipAddress);
        
        Map<CmdFeature, Boolean> rs = new LinkedHashMap<>();
        for (CmdFeature feature : supportedFeatures) {
            boolean status = false;
            try {
                status = feature.check(server);
            } catch (Exception e) {
                e.printStackTrace();
            }
            rs.put(feature, status);
        }
        return rs;
    }

    @Override
    public SSHResult installFeature(String ipAddress, String featureName) throws Exception {
        Server server = serverDao.findByServerIP(ipAddress);
        
        CmdFeature sshFeature = features.get(featureName);
        return sshFeature.install(server);
    }

    @Override
    public SSHResult uninstallFeature(String ipAddress, String featureName) throws Exception {
        Server server = serverDao.findByServerIP(ipAddress);
        
        CmdFeature sshFeature = features.get(featureName);
        return sshFeature.uninstall(server);
    }
}
