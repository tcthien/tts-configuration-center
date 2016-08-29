package com.tts.app.configcenter.model.server;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tts.app.configcenter.model.zone.Zone;
import com.tts.lib.model.generic.GenericModel;

@NamedQueries({
    @NamedQuery(name = Server.GET_SERVERS, 
        query = "SELECT DISTINCT obj "
                + "FROM Server obj "
                + "ORDER BY obj.name"),
    @NamedQuery(name = Server.GET_SERVER_BY_ID, 
        query = "SELECT DISTINCT obj "
                + "FROM Server obj "
                + "WHERE obj.id=:serverId "
                + "ORDER BY obj.name"),
})
@Entity(name = "Server")
@Table(name = "Server")
@XmlType
@XmlRootElement
public class Server extends GenericModel {
    public static final String GET_SERVERS = "GET_SERVERS";
    public static final String GET_SERVER_BY_ID = "GET_SERVER_BY_ID";
    
    private String name;
    private String userName;
    private String password;
    private String ipAddress;
    
    @ManyToOne(optional = false)
    private Zone zone;
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
    
    public Zone getZone() {
        return zone;
    }
    
    public void setZone(Zone zone) {
        this.zone = zone;
    }
}
