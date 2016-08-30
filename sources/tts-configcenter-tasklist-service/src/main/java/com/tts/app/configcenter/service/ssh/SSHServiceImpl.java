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
import com.tts.app.configcenter.service.ssh.cmd.Command;
import com.tts.app.configcenter.service.ssh.cmd.PingCommand;
import com.tts.app.configcenter.ssh.feature.CmdFeature;
import com.tts.app.configcenter.ssh.feature.DockerComposeFeature;
import com.tts.app.configcenter.ssh.feature.DockerFeature;

@Named(value = "sshServiceImpl")
public class SSHServiceImpl implements SSHService {
    
    @OsgiService @Inject
    ServerDao serverDao;
    
    @Inject
    SSHCommandExecutor commandExecutor;
    
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
    public boolean ping(String srIpAddress, String desIpAddress) throws Exception {
        Server server = serverDao.findByServerIP(srIpAddress);
        SSHResult rs = new PingCommand(desIpAddress).execute(commandExecutor, server);
        return rs.getExistStatus() == SSHResult.PING_REACHABLE;
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
