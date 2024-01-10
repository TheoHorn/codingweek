package eu.telecomnancy.directdealing.model.offer;

import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.content.Content;

/**
 * Request class
 */
public class Request extends Offer {
    /**
     * Constructor of the request
     * @param owner Owner of the request
     * @param content Content of the request
     * @param slot Slot of the request
     * @param request Boolean to know if the request is a request or a proposal
     */
    public Request(User owner, Content content, Slot slot,  boolean request){
        // Request is a subclass of Offer and inherits its constructor
        super(owner, content, slot, request);
    }
}
