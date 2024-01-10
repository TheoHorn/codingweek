package eu.telecomnancy.directdealing.model.offer;

import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.content.Content;

import java.sql.SQLException;

/**
 * Request class
 */
public class Request extends Offer {

    public Request(int idOffer, String mail, boolean isRequest, int idContent) throws SQLException {
        // Request is a subclass of Offer and inherits its constructor
        super(idOffer, mail, isRequest, idContent);
    }
    public Request(String mail, boolean isRequest, int idContent) throws SQLException {
        // Request is a subclass of Offer and inherits its constructor
        super(mail, isRequest, idContent);
    }
}
