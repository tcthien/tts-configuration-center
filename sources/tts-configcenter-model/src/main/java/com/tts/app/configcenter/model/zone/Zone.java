package com.tts.app.configcenter.model.zone;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tts.fwk.util.TTSPojoUtil;
import com.tts.lib.model.generic.GenericModel;

@Entity(name = "Zone")
@Table(name = "Zone")
@XmlType
@XmlRootElement
public class Zone extends GenericModel {

    private String name;
    
    @Override
    public String toString() {
        return TTSPojoUtil.toString("Zone: " + name);
    }
    
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
    
}
