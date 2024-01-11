package eu.telecomnancy.directdealing.views.home;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.model.offer.Proposal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;

import static eu.telecomnancy.directdealing.Main.app;

/**
 * OfferCell class
 */
public class OfferCell extends ListCell<Offer> {
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
    private Label avisLabel;
    /**
     * FXMLLoader
     */
    private FXMLLoader mLLoader;
    private Application app;

    public OfferCell(){
        super();
        this.app = Application.getInstance();
    }

    /**
     * update the item of the cell
     * @param offer Offer to update
     * @param b Boolean to know if the offer is null
     */
    @Override
    protected void updateItem(Offer offer, boolean b) {
        super.updateItem(offer, b);
        if (b || offer == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/home/offer_cell.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            try {
                image.setImage(new Image(app.getContentDAO().get(offer.getIdContent()).getImage().toURI().toString()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            if (offer instanceof Proposal) {
                type.setText("Offre");
            } else {
                type.setText("Demande");
            }

            try {
                service.setText(app.getContentDAO().get(offer.getIdContent()).isEquipment() ? "Bien" : "Service");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                title.setText(app.getContentDAO().get(offer.getIdContent()).getTitle());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                double priceSet = app.getContentDAO().get(offer.getIdContent()).getPrice();
                price.setText(String.valueOf(priceSet));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                category.setText(app.getContentDAO().get(offer.getIdContent()).getCategory());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                place.setText(app.getContentDAO().get(offer.getIdContent()).getLocalisation());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                avisLabel.setText("Avis sur l'utilisateur : "+ String.valueOf(app.getEvaluationManager().getAverage(offer.getMail()))+"/5 ("+app.getEvaluationManager().getEvaluationCount(offer.getMail())+")");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            setText(null);
            setGraphic(mLLoader.getRoot());
        }
    }

    @FXML
    public void displayOffer() throws Exception {
        if (app.getCurrentUser() instanceof User){
            app.setLastOffer(getItem());
            app.getSceneController().switchToOfferDisplay();
        } else {
            app.deleteOffer(getItem());
        }
    }

    @FXML
    public void displayForeignProfil() throws Exception {
        Application.getInstance().setLastAccount(app.getAccountDAO().get(getItem().getMail()));
        Application.getInstance().getSceneController().switchToProfileDisplay();
    }
}
