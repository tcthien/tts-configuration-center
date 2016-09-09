package com.tts.app.configcenter.persistence.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.tts.app.configcenter.model.server.Server;
import com.tts.app.configcenter.model.server.ServerDao;
import com.tts.app.configcenter.model.zone.Zone;
import com.tts.app.configcenter.model.zone.ZoneDao;
import com.tts.app.configcenter.persistence.impl.server.ServerDaoImpl;
import com.tts.app.configcenter.persistence.impl.zone.ZoneDaoImpl;

public class AbstractDaoTest {

    public static Object sync = new Object();
    public static boolean alreadyInitialize = false;
    
    protected ServerDao serverDao = new ServerDaoImpl();
    protected ZoneDao zoneDao = new ZoneDaoImpl();
    protected EntityManager em;

    public AbstractDaoTest() {
        initialize();
    }
    
    protected void initialize() {
        if (!alreadyInitialize) {
            synchronized (sync) {
                if (!alreadyInitialize) {
                    initDao();
                    
                    beginTransaction();
                    initData();
                    commitTransaction();
                }
            }
        }
    }

    protected void initData() {
        Zone zone1 = new Zone(1L, "Zone 1");
        Zone zone2 = new Zone(2L, "Zone 2");
        zone1 = zoneDao.add(zone1);
        zone2 = zoneDao.add(zone2);
        
        Server server1 = new Server();
        server1.setZone(zone1);
        server1.setDescription("Description");
        server1.setIpAddress("192.168.1.1");
        server1.setPassword("pwd");
        server1.setServerName("Server 1");
        server1.setUserName("user1");
        server1 = serverDao.add(server1);
        
        Server server2 = new Server();
        server2.setZone(zone2);
        server2.setDescription("Description");
        server2.setIpAddress("192.168.1.2");
        server2.setPassword("pwd");
        server2.setServerName("Server 2");
        server2.setUserName("user2");
        server2 = serverDao.add(server2);
    }

    protected void initDao() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("configcenter", System.getProperties());
        em = emf.createEntityManager();
        
        ((ServerDaoImpl)serverDao).setEntityManager(em);
        ((ZoneDaoImpl)zoneDao).setEntityManager(em);
    }
    
    protected void beginTransaction() {
        em.getTransaction().begin();
    }
    
    protected void commitTransaction() {
        em.getTransaction().commit();
    }
}
