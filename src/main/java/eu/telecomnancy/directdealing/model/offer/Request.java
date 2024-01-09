package eu.telecomnancy.directdealing.model.offer;

import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.content.Content;

public class Request extends Offer {
    public Request(User owner, Content content, Slot slot,  boolean request){
        // Request is a subclass of Offer and inherits its constructor
        super(owner, content, slot, request);
    }
}
