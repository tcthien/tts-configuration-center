package com.tts.app.configcenter.model.ssh;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@XmlRootElement
public class ConsoleLog {
    private String log;

    public ConsoleLog() {
    }

    public ConsoleLog(String log) {
        this.log = log;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
