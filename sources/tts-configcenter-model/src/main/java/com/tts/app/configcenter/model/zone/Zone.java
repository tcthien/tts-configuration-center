package com.tts.app.configcenter.model.zone;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tts.app.configcenter.model.server.Server;
import com.tts.lib.model.generic.GenericModel;


@NamedQueries({
    @NamedQuery(name = Zone.GET_ZONES, 
        query = "SELECT DISTINCT obj "
                + "FROM Zone obj "
                + "ORDER BY obj.name"),
    @NamedQuery(name = Zone.GET_ZONE_BY_ID, 
        query = "SELECT DISTINCT obj "
                + "FROM Zone obj "
                + "WHERE obj.id=:zoneId "
                + "ORDER BY obj.name"),
})
@Entity(name = "Zone")
@Table(name = "Zone")
@XmlType
@XmlRootElement
public class Zone extends GenericModel {
    public static final String GET_ZONES = "GET_ZONES";
    public static final String GET_ZONE_BY_ID = "GET_ZONE_BY_ID";

    private String name;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "zone")
    private List<Server> servers;

    public Zone() {
    }
    
    public Zone(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<Server> getServers() {
        return servers;
    }
    
    public void setServers(List<Server> servers) {
        this.servers = servers;
    }
}
