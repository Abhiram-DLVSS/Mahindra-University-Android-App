package com.example.turing_login;

public class datamodel_mon {

    String header,desc;

    public datamodel_mon(String header, String desc) {
        this.header = header;
        this.desc = desc;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
