package com.example.turing_login;

public class Listitem_assign {
    private String head;
    private String desc;
    private String lect;
    private String link;

    public Listitem_assign(String head, String desc, String lect, String link) {
        this.head = head;
        this.desc = desc;
        this.lect= lect;
        this.link=link;
    }

    public String getHead() { return head; }

    public String getDesc() { return desc; }

    public String getLect() { return lect; }

    public String getLink() {return link;}

}
