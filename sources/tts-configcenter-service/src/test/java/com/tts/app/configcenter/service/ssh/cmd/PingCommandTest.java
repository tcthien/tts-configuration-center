package com.tts.app.configcenter.service.ssh.cmd;

import org.junit.Assert;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.service.ssh.SSHResult;

public class PingCommandTest extends AbstractCommandTest {

    public void testExecute() throws Exception {
        // Going to Ping 192.168.100.1
        PingCommand cmd = new PingCommand("192.168.100.1");
        // SSH to server 192.168.100.100
        Server server = new Server();
        server.setIpAddress("192.168.100.100");
        server.setUserName("tcthien");
        server.setPassword("tcthien");
        
        SSHResult rs = cmd.execute(executor, server);
        Assert.assertEquals(SSHResult.PING_REACHABLE, rs.getExistStatus());
    }
    
    public void testExecute1() throws Exception {
        // Going to Ping 192.168.100.1
        PingCommand cmd = new PingCommand("192.168.3.4");
        // SSH to server 192.168.100.100
        Server server = new Server();
        server.setIpAddress("192.168.100.100");
        server.setUserName("tcthien");
        server.setPassword("tcthien");
        
        SSHResult rs = cmd.execute(executor, server);
        Assert.assertEquals(SSHResult.PING_UNREACHABLE, rs.getExistStatus());
    }
}
