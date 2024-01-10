package eu.telecomnancy.directdealing.views.home;

import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.model.offer.Proposal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

/**
 * OfferCell class
 */
public class OfferCell extends ListCell<Offer> {
    @FXML
    private ImageView image;
    @FXML
    private Label type;
    @FXML
    private Label title;
    @FXML
    private Label price;
    @FXML
    private Label category;
    @FXML
    private Label place;
    /**
     * FXMLLoader
     */
    private FXMLLoader mLLoader;

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

            if (offer instanceof Proposal) {
                type.setText("Offre");
            } else {
                type.setText("Demande");
            }
            title.setText(offer.getContent().getTitle());
            price.setText(String.valueOf(offer.getContent().getPrice()));
            category.setText(offer.getContent().getCategory());
            place.setText(offer.getContent().getLocalisation());

            setText(null);
            setGraphic(mLLoader.getRoot());
        }
    }
}
