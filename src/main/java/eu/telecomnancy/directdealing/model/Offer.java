package eu.telecomnancy.directdealing.model;

public abstract class Offer {
    private User owner;
    private Content content;
    private int id;
    private static int currentId;
    private boolean request;
    private Creneau creneau;

    public Offer(User owner, boolean request, Content content, Creneau creneau) {
        this.request = request;
        this.creneau = creneau;
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

    public Creneau getCreneau() {
        return creneau;
    }

    public boolean isRequest(){
        return this.request;
    }
}
