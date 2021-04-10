package com.example.turing_login;

public class Listitem_monfrag {
    private String head;
    private String desc;
    private String lect;
    private String col;

    public Listitem_monfrag(String head, String desc, String lect,String col) {
        this.head = head;
        this.desc = desc;
        this.lect= lect;
        this.col=col;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public String getLect() { return lect; }

    public String getCol() { return col; }
    
    


}
