package com.example.web.pojo;

public class SidecarReq {
    private Boolean justTrigger;
    private String ID;
    private String Name;
    private String type;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getJustTrigger() {
        return justTrigger;
    }

    public void setJustTrigger(Boolean justTrigger) {
        this.justTrigger = justTrigger;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}

