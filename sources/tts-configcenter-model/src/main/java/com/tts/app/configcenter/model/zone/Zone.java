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

    private String zoneName;

    @Override
    public String toString() {
        return TTSPojoUtil.toString("Zone: " + zoneName);
    }

    public Zone() {
    }

    public Zone(Integer id, String zoneName) {
        this.id = id;
        this.zoneName = zoneName;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

}
