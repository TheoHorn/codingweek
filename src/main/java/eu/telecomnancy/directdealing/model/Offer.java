package eu.telecomnancy.directdealing.model;

public abstract class Offer {
    private User owner;
    private Content content;
    private int id;
    private static int currentId;
    private boolean request;

    public Offer(User owner, Content content) {
        this.owner = owner;
        this.content = content;
        this.id = currentId;
        currentId++;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public int getId(){
        return this.id;
    }

    public boolean isRequest(){
        return this.request;
    }
}
