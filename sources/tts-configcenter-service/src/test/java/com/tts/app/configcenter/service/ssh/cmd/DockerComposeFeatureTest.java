package com.tts.app.configcenter.service.ssh.cmd;

import org.junit.Assert;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.service.ssh.SSHCommandExecutor;
import com.tts.app.configcenter.service.ssh.SSHCommandExecutorImpl;
import com.tts.app.configcenter.service.ssh.feature.DockerComposeFeature;

public class DockerComposeFeatureTest {

    SSHCommandExecutor executor = new SSHCommandExecutorImpl();
    
    public void testCheck() throws Exception {
        DockerComposeFeature f = new DockerComposeFeature(executor);
        Server server = new Server();
        server.setIpAddress("192.168.100.100");
        server.setUserName("tcthien");
        server.setPassword("tcthien");
        boolean status = f.check(server);
        Assert.assertTrue(status);
    }
}
