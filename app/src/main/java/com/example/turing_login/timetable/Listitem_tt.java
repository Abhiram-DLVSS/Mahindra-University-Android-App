package com.example.turing_login.timetable;

public class Listitem_tt {
    private final String head;
    private final String desc;
    private final String lect;
    private final String col;
    private final String link;

    public Listitem_tt(String head, String desc, String lect, String col, String link) {
        this.head = head;
        this.desc = desc;
        this.lect= lect;
        this.col=col;
        this.link=link;
    }

    public String getHead() { return head; }

    public String getDesc() { return desc; }

    public String getLect() { return lect; }

    public String getCol() { return col; }

    public String getLink() {return link;}

}
