package com.tts.app.configcenter.model.server;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tts.app.configcenter.model.zone.Zone;
import com.tts.fwk.util.TTSPojoUtil;
import com.tts.lib.model.generic.GenericModel;

@Entity(name = "Server")
@Table(name = "Server")
@XmlType
@XmlRootElement
public class Server extends GenericModel {
    
    private String name;
    private String userName;
    private String password;
    private String ipAddress;
    private String description;
    
    @Override
    public String toString() {
        return TTSPojoUtil.toString("Server: " + name);
    }
    
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
