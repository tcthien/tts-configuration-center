package com.tts.app.configcenter.service.ssh.cmd;

import org.junit.Assert;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.service.ssh.SSHCommandExecutor;
import com.tts.app.configcenter.service.ssh.SSHCommandExecutorImpl;
import com.tts.app.configcenter.service.ssh.SSHResult;
import com.tts.app.configcenter.service.ssh.feature.DockerFeature;

public class DockerFeatureTest {

    private SSHCommandExecutor executor;
    private DockerFeature feature;
    
    public DockerFeatureTest() {
        executor = new SSHCommandExecutorImpl();
        feature = new DockerFeature(executor);
    }
    
    public void testCheck() throws Exception {
        Server server = createServer();
        boolean status = feature.check(server);
        Assert.assertTrue(status);
    }

    protected Server createServer() {
        Server server = new Server();
        server.setIpAddress("192.168.100.100");
        server.setUserName("tcthien");
        server.setPassword("tcthien");
        return server;
    }
    
    public void testUninstallDocker() throws Exception {
        SSHResult rs = feature.uninstall(createServer(), false);
        Assert.assertEquals(SSHResult.STATUS_OK, rs.getExistStatus());
    }
    
    public void testInstallDocker() throws Exception {
        SSHResult rs = feature.install(createServer());
        Assert.assertEquals(SSHResult.STATUS_OK, rs.getExistStatus());
    }
}
