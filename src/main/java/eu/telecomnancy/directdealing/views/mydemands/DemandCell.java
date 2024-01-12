package eu.telecomnancy.directdealing.views.mydemands;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.content.Content;
import eu.telecomnancy.directdealing.model.demande.Demande;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.model.offer.Proposal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import java.sql.SQLException;

import static eu.telecomnancy.directdealing.Main.app;

/**
 * OfferCell class
 */
public class DemandCell extends ListCell<Demande> {
    @FXML
    private ImageView image;
    @FXML
    private Label type;
    @FXML
    private Label service;
    @FXML
    private Label title;
    @FXML
    private Label price;
    @FXML
    private Label category;
    @FXML
    private Label place;

    @FXML
    private Label creneau;

    @FXML
    private Label status;
    @FXML
    private Button cancelButton;
    /**
     * FXMLLoader
     */
    @FXML
    private Rectangle rec_status;
    private FXMLLoader mLLoader;

    /**
     * update the item of the cell
     * @param demande Offer to update
     * @param b Boolean to know if the offer is null
     */
    @Override
    protected void updateItem(Demande demande, boolean b) {
        super.updateItem(demande, b);
        if (b || demande == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/demand/demand_cell.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Slot slot = app.getSlotDAO().get(demande.getIdSlot(),false);
                Offer offer = app.getOfferDAO().get(slot.getIdOffer());

                image.setImage(new Image(app.getContentDAO().get(offer.getIdContent()).getImage().toURI().toString()));
                if (offer instanceof Proposal) {
                    type.setText("Offre");
                } else {
                    type.setText("Demande");
                }
                service.setText(app.getContentDAO().get(offer.getIdContent()).isEquipment() ? "Bien" : "Service");
                title.setText(app.getContentDAO().get(offer.getIdContent()).getTitle());
                double priceSet = app.getContentDAO().get(offer.getIdContent()).getPrice();
                price.setText(String.valueOf(priceSet));
                category.setText(app.getContentDAO().get(offer.getIdContent()).getCategory());
                place.setText(app.getContentDAO().get(offer.getIdContent()).getLocalisation());

                creneau.setText(slot.toString());
                int status = demande.getStatus();
                if (status == 0) {
                    this.status.setText("En attente");
                    this.rec_status.setStyle("-fx-fill: #FFC107");
                } else if (status == 1) {
                    this.status.setText("Acceptée");
                    this.rec_status.setStyle("-fx-fill: #4CAF50");
                    this.cancelButton.setVisible(false);

                } else {
                    this.status.setText("Refusée");
                    this.rec_status.setStyle("-fx-fill: #F44336");
                    this.cancelButton.setVisible(false);

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }



            setText(null);
            setGraphic(mLLoader.getRoot());
        }
    }

    @FXML
    public void cancelReservation() throws Exception {
        Application.getInstance().setLastDemand(getItem());
        app.deleteDemande();
    }
}
