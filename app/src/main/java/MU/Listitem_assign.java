package MU;

public class Listitem_assign {
    private final String head;
    private final String desc;
    private final String lect;
    private final String link;

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
