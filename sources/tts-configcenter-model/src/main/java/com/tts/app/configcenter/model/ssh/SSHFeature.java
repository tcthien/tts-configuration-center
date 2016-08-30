package com.tts.app.configcenter.model.ssh;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tts.fwk.util.TTSPojoUtil;

@XmlType
@XmlRootElement
public class SSHFeature {
    private String name;
    private String osName;
    private String osVersion;
    private String description;
    private Boolean status;
    
    public SSHFeature() {
    }
    
    public SSHFeature(String name, String osName, String osVersion, String description) {
        this.name = name;
        this.osName = osName;
        this.osVersion = osVersion;
        this.description = description;
    }
    
    public static SSHFeature create() {
        return new SSHFeature();
    }
    
    public SSHFeature name(String name) {
        setName(name);
        return this;
    }
    public SSHFeature osName(String osName) {
        setOsName(osName);
        return this;
    }
    public SSHFeature osVersion(String osVersion) {
        setOsVersion(osVersion);
        return this;
    }
    public SSHFeature description(String description) {
        setDescription(description);
        return this;
    }
    
    @Override
    public String toString() {
        return TTSPojoUtil.toString(name, osName, osVersion);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof SSHFeature)) {
            return false;
        }
        SSHFeature oth = (SSHFeature) obj;
        if (!TTSPojoUtil.equalsObject(name, oth.name)) {
            return false;
        }
        if (!TTSPojoUtil.equalsObject(osName, oth.osName)) {
            return false;
        }
        if (!TTSPojoUtil.equalsObject(osVersion, oth.osVersion)) {
            return false;
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return TTSPojoUtil.buildHashCode(name, osName, osVersion);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean isStatus() {
        return status;
    }
}
