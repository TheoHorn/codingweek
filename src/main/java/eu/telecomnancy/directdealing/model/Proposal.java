package eu.telecomnancy.directdealing.model;

public class Proposal extends Offer {
    private Integer price;
    public Proposal(User owner, Content content, Integer price) {
        super(owner, content);
        this.price = price;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
