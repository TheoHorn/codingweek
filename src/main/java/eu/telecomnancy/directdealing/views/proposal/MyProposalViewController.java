package eu.telecomnancy.directdealing.views.proposal;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.views.home.OfferCell;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * MyProposalViewController class
 */
public class MyProposalViewController implements Observer {
    /**
     * ListView for the proposals
     */
    @FXML
    private ListView<Offer> myOffersListView;
    /**
     * Application instance
     */
    private Application app;

    /**
     * Constructor of my proposal view controller
     */
    public MyProposalViewController() {
        this.app = Application.getInstance();
        this.app.addObserver(this);
    }

    /**
     * update the view
     */
    @Override
    public void update() throws Exception {
        myOffersListView.getItems().clear();
        this.app.getOfferDAO().get(app.getCurrentUser().getEmail()).forEach(offer -> myOffersListView.getItems().add(offer));
        myOffersListView.setCellFactory(lv -> new OfferCell());
    }
}
