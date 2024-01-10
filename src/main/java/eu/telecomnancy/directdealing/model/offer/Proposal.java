package eu.telecomnancy.directdealing.model.offer;

import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.content.Content;

/**
 * Proposal class
 */
public class Proposal extends Offer {
    /**
     * Constructor of the proposal
     * @param owner Owner of the proposal
     * @param content Content of the proposal
     * @param slot Slot of the proposal
     * @param request Boolean to know if the proposal is a request or a proposal
     */
    public Proposal(User owner, Content content, Slot slot, boolean request) {

        super(owner, content, slot, request);
    }

}
