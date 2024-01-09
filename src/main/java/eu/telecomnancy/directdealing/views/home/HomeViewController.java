package eu.telecomnancy.directdealing.views.home;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.model.offer.Offer;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;

public class HomeViewController implements Observer {
    @FXML
    private ListView<Offer> offersListView;
    private Application app;

    public HomeViewController() {
        this.app = Application.getInstance();
    }

    @Override
    public void update() {
        offersListView.getItems().clear();
        this.app.getOffers().forEach(offer -> offersListView.getItems().add(offer));
        offersListView.setCellFactory(lv -> new OfferCell());
    }
}
