package com.tts.app.configcenter.persistence.impl.server;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.model.server.ServerQueryFilter;
import com.tts.app.configcenter.persistence.impl.AbstractDaoTest;

public class ServerDaoImplTest extends AbstractDaoTest {
    
    @Test
    public void testFindByIPAddress() throws Exception {
        beginTransaction();
        Server server = serverDao.findByServerIP("192.168.1.1");
        commitTransaction();
        Assert.assertEquals("Server 1", server.getServerName());
    }
    
    @Test
    public void testFindByZoneId1() throws Exception {
        ServerQueryFilter filter = new ServerQueryFilter();
        filter.setZoneIds(Arrays.asList("1"));
        
        beginTransaction();
        List<Server> servers = serverDao.findByQuery(filter);
        commitTransaction();
        
        Assert.assertEquals("Server 1", servers.iterator().next().getServerName());
        Assert.assertEquals(1, servers.size());
    }
    
    @Test
    public void testFindByZoneId2() throws Exception {
        ServerQueryFilter filter = new ServerQueryFilter();
        filter.setZoneIds(Arrays.asList("1", "2"));
        
        beginTransaction();
        List<Server> servers = serverDao.findByQuery(filter);
        commitTransaction();
        
        Assert.assertEquals("Server 1", servers.get(0).getServerName());
        Assert.assertEquals("Server 2", servers.get(1).getServerName());
    }
    
    @Test
    public void testFindByFilter() throws Exception {
        ServerQueryFilter filter = new ServerQueryFilter();
        filter.setIpAddress("192.168.1.1");
        filter.setZoneIds(Arrays.asList("1"));
        
        beginTransaction();
        List<Server> servers = serverDao.findByQuery(filter);
        commitTransaction();
        
        Assert.assertEquals("Server 1", servers.iterator().next().getServerName());
    }
}
