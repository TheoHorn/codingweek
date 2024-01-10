package eu.telecomnancy.directdealing.views.home;

import eu.telecomnancy.directdealing.model.offer.Offer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

/**
 * OfferCell class
 */
public class OfferCell extends ListCell<Offer> {
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

            // TODO

            setText(null);
            setGraphic(mLLoader.getRoot());
        }
    }
}
