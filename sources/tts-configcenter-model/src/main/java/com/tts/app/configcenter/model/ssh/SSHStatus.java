package com.tts.app.configcenter.model.ssh;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import com.tts.fwk.util.TTSPojoUtil;

@XmlType
@XmlRootElement
public class SSHStatus {
    
    private String status;
    
    @Override
    public String toString() {
        return TTSPojoUtil.toString(status);
    }
    
    @Override
    public boolean equals(Object obj) {
        if(obj == null || !(obj instanceof SSHStatus)) {
            return false;
        }
        SSHStatus oth = (SSHStatus) obj;
        return TTSPojoUtil.equalsObject(status, oth.status);
    }
    
    @Override
    public int hashCode() {
        return TTSPojoUtil.buildHashCode(status);
    }
    
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
