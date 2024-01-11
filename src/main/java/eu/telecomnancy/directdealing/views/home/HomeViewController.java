package eu.telecomnancy.directdealing.views.home;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.model.offer.Offer;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * HomeViewController class
 */
public class HomeViewController implements Observer {
    /**
     * ListView of the offers
     */
    @FXML
    protected ListView<Offer> offersListView;
    /**
     * Application instance
     */
    protected Application app;

    /**
     * Constructor of the home view controller
     */
    public HomeViewController() {
        this.app = Application.getInstance();
        this.app.addObserver(this);
    }

    /**
     * update method
     */
    @Override
    public void update() throws Exception {
        offersListView.getItems().clear();
        this.app.getResearchFilterManager().filtersHomeOffers().forEach(offer -> offersListView.getItems().add(offer));
        offersListView.setCellFactory(lv -> new OfferCell());
    }
}
