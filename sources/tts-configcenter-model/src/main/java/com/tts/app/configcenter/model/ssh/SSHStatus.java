package com.tts.app.configcenter.model.ssh;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tts.lib.model.generic.GenericModel;

@XmlType
@XmlRootElement
public class SSHStatus extends GenericModel {
    
    private String status;
    
    public SSHStatus() {
    }
    
    public SSHStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
