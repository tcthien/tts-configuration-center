package com.tts.app.configcenter.model.server;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tts.app.configcenter.model.util.QueryFilter;

@XmlType
@XmlRootElement
public class ServerQueryFilter extends QueryFilter {
    private List<String> zoneIds;
    private String ipAddress;

    public List<String> getZoneIds() {
        return zoneIds;
    }

    public void setZoneIds(List<String> zoneIds) {
        this.zoneIds = zoneIds;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
