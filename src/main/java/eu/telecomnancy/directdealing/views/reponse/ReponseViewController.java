package eu.telecomnancy.directdealing.views.reponse;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Demande;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.views.home.OfferCell;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ReponseViewController implements Observer {

    @FXML
    protected ListView<Demande> answersListView;
    private Application app;

    /**
     * Constructor of reponse view controller
     */
    public ReponseViewController() {
        this.app = Application.getInstance();
        this.app.addObserver(this);
    }
    /**
     * update method
     */
    @Override
    public void update() throws Exception {
        // TODO
    }
}
