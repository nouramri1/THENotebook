public class Link {
    private String type;
    private String link;

    public Link(String type, String link) {
        this.type = type;
        this.link = link;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return type + ": " + link;
    }
}
