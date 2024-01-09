package eu.telecomnancy.directdealing.model.offer;

import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.content.Content;

public class Request extends Offer {
    public Request(User owner, Content content) {
        super(owner, content);
    }
}
