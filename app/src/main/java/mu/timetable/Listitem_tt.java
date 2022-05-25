package mu.timetable;

public class Listitem_tt {
    private final String head;
    private final String desc;
    private final String lect;
    private final String col;
    private final String link;
    private final String loc;

    public Listitem_tt(String head, String desc, String lect, String col, String link, String loc) {
        this.head = head;
        this.desc = desc;
        this.lect = lect;
        this.col = col;
        this.link = link;
        this.loc = loc;
    }

    public String getHead() {
        return head;
    }

    public String getDesc() {
        return desc;
    }

    public String getLect() {
        return lect;
    }

    public String getCol() {
        return col;
    }

    public String getLink() {
        return link;
    }

    public String getLoc() {
        return loc;
    }

}
