package eu.telecomnancy.directdealing.model;

public abstract class Offer {
    private User owner;
    private Content content;

    public Offer(User owner, Content content) {
        this.owner = owner;
        this.content = content;
    }
}
