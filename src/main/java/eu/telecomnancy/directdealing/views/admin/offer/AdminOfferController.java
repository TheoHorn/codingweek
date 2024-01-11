package eu.telecomnancy.directdealing.views.admin.offer;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.model.offer.Offer;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class AdminOfferController implements Observer {
    /**
     * ListView of the offers
     */
    protected ListView<Offer> offersListView;
    /**
     * Application instance
     */
    private Application app;

    public AdminOfferController(ListView<Offer> offersListView){
        this.offersListView = offersListView;
        this.app = Application.getInstance();
        app.addObserver(this);
    }

    @Override
    public void update() throws Exception {
        // TODO
    }
}
