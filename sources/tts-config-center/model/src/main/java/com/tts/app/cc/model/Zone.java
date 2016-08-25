package com.tts.app.cc.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Zone {
    private Integer id;
    private String name;

    public Zone() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
