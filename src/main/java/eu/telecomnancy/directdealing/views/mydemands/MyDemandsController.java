package eu.telecomnancy.directdealing.views.mydemands;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.views.home.OfferCell;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MyDemandsController implements Observer {

    /**
     * ListView of the offers
     */
    @FXML
    protected ListView<Offer> myDemandsListView;
    /**
     * Application instance
     */
    protected Application app;

    /**
     * Constructor of the home view controller
     */
    public  MyDemandsController() {
        this.app = Application.getInstance();
        this.app.addObserver(this);
    }

    /**
     * update method
     */
    @Override
    public void update() throws Exception {
        myDemandsListView.getItems().clear();
        this.app.getOffers().forEach(offer -> myDemandsListView.getItems().add(offer));
        myDemandsListView.setCellFactory(lv -> new DemandCell());
    }
}
