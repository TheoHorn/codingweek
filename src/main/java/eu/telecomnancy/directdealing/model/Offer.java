package eu.telecomnancy.directdealing.model;

public abstract class Offer {
    private User owner;
    private Content content;
    private int id;
    private static int currentId;
    private boolean request;
    private Slot slot;

    public Offer(User owner, boolean request, Content content, Slot slot) {
        this.request = request;
        this.slot = slot;
        this.owner = owner;
        this.content = content;
        this.id = currentId;
        currentId++;
    }

    public Offer(User owner, Content content) {
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

    public Slot getSlot() {
        return this.slot;
    }

    public boolean isRequest(){
        return this.request;
    }
}
