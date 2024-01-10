package eu.telecomnancy.directdealing.model.offer;

import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.content.Content;

import java.sql.SQLException;

/**
 * Proposal class
 */
public class Proposal extends Offer {

    public Proposal(int idOffer, String mail, boolean isRequest, int idContent) throws SQLException {
        super(idOffer, mail, isRequest, idContent);
    }

    public Proposal(String mail, boolean isRequest, int idContent) throws SQLException {
        super(mail, isRequest, idContent);
    }

}
