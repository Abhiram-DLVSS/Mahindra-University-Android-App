package com.example.turing_login;

public class Listitem_form {
    private final String head;
    private final String desc;
    private final String lect;
    private final String link;

    public Listitem_form(String head, String desc, String lect, String link) {
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
