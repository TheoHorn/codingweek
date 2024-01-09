package eu.telecomnancy.directdealing.views.home;

import eu.telecomnancy.directdealing.model.offer.Offer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;


public class OfferCell extends ListCell<Offer> {
    private FXMLLoader mLLoader;
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
