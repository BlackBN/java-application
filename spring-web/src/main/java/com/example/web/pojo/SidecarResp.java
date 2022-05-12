package com.example.web.pojo;

import java.util.List;

public class SidecarResp {
    private String ID;

    private List<String> Address;


    public SidecarResp(String ID, List<String> address) {
        this.ID = ID;
        Address = address;
    }

    public SidecarResp() {
    }

    public List<String> getAddress() {
        return Address;
    }

    public void setAddress(List<String> address) {
        Address = address;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
}
