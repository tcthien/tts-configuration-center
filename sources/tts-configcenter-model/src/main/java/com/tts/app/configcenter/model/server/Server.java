package com.tts.app.configcenter.model.server;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
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

    private String serverName;
    private String userName;
    @Column(name = "sudoPassword")
    private String password;
    private String ipAddress;
    private String description;
    @ManyToOne
    private Zone zone;
    public Zone getZone() {
        return zone;
    }

    @Override
    public String toString() {
        return TTSPojoUtil.toString("Server: " + serverName);
    }

    public String getUserName() {
        return userName;z
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

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServerName() {
        return serverName;
    }

    public void setServerName(String serverName) {
        this.serverName = serverName;
    }
}
