package com.tts.app.configcenter.service.ssh.cmd;

import org.junit.Assert;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.service.ssh.SSHResult;
import com.tts.app.configcenter.service.ssh.cmd.docker.InstallDockerComposeCommand;

public class InstallDockerComposeCommandTest extends AbstractCommandTest {

    public void testExecute() throws Exception {
        InstallDockerComposeCommand cmd = new InstallDockerComposeCommand("tcthien");
        // SSH to server 192.168.100.100
        Server server = new Server();
        server.setIpAddress("192.168.100.100");
        server.setUserName("tcthien");
        server.setPassword("tcthien");
        
        SSHResult rs = cmd.execute(executor, server);
        Assert.assertEquals(SSHResult.STATUS_OK, rs.getExistStatus());
    }
}
