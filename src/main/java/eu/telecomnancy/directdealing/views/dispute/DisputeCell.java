package eu.telecomnancy.directdealing.views.dispute;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.account.Account;
import eu.telecomnancy.directdealing.model.content.Content;
import eu.telecomnancy.directdealing.model.demande.Demande;
import eu.telecomnancy.directdealing.model.dispute.Dispute;
import eu.telecomnancy.directdealing.model.messaging.Messaging;
import eu.telecomnancy.directdealing.model.offer.Offer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuButton;

import java.sql.SQLException;
import java.util.List;
import static eu.telecomnancy.directdealing.Main.app;

public class DisputeCell extends ListCell<Dispute> {
    @FXML
    private Label title;
    @FXML
    private Label defender;
    /**
     * FXMLLoader
     */

    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Dispute dispute, boolean b) {
        super.updateItem(dispute, b);
        if (b || dispute == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/dispute/dispute_cell.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                int idDemande = dispute.getIdDemande();
                Demande demande = app.getDemandeDAO().get(idDemande);
                Slot slot = app.getSlotDAO().get(demande.getIdSlot(),false);
                Offer offer = app.getOfferDAO().get(slot.getIdOffer());
                Content content = app.getContentDAO().get(offer.getIdContent());
                this.title.setText(content.getTitle());
                this.defender.setText(dispute.getDefender());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            setText(null);
            setGraphic(mLLoader.getRoot());
        }
    }




}
