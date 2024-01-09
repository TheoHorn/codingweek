package eu.telecomnancy.directdealing.model.offer;

import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.content.Content;

public class Proposal extends Offer {
    public Proposal(User owner, Content content, Slot slot, boolean request) {
        super(owner, content, slot, request);
    }

}
