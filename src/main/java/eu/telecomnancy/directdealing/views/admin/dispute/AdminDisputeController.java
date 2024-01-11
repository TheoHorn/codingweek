package eu.telecomnancy.directdealing.views.admin.dispute;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Dispute;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.model.offer.Offer;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class AdminDisputeController implements Observer {
    /**
     * ListView of the offers
     */
    @FXML
    protected ListView<Dispute> disputesListView;
    /**
     * Application instance
     */
    private Application app;

    public AdminDisputeController(){
        this.app = Application.getInstance();
        app.addObserver(this);
    }

    @Override
    public void update() throws Exception {
        // TODO
    }
}
