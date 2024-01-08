package eu.telecomnancy.directdealing.model;

public abstract class Offer {
    private User owner;
    private Content content;

    public Offer(User owner, Content content) {
        this.owner = owner;
        this.content = content;
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
}
