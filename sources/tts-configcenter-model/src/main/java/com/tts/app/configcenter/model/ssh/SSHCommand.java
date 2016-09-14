package com.tts.app.configcenter.model.ssh;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tts.fwk.util.TTSPojoUtil;

@XmlType
@XmlRootElement
public class SSHCommand {

    private String name;
    private String description;
    private SSHFeature feature;
    
    public SSHCommand() {
    }
    
    public SSHCommand(SSHFeature sshFeature, String name, String description) {
        this.feature = sshFeature;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return TTSPojoUtil.toString(name);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || !(obj instanceof SSHCommand)) {
            return false;
        }
        SSHCommand oth = (SSHCommand) obj;
        if (!TTSPojoUtil.equalsObject(name, oth.name)) {
            return false;
        }
        if (!TTSPojoUtil.equalsObject(feature, oth.feature)) {
            return false;
        }
        return TTSPojoUtil.equalsObject(description, oth.description);
    }

    @Override
    public int hashCode() {
        return TTSPojoUtil.buildHashCode(name, description, feature);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SSHFeature getFeature() {
        return feature;
    }

    public void setFeature(SSHFeature feature) {
        this.feature = feature;
    }

}
