package eu.telecomnancy.directdealing.views.admin.dispute;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.dispute.Dispute;
import eu.telecomnancy.directdealing.model.Observer;
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

    public AdminDisputeController(ListView<Dispute> disputesListView){
        this.disputesListView = disputesListView;
        this.app = Application.getInstance();
        app.addObserver(this);
    }

    @Override
    public void update() throws Exception {
        // TODO
    }
}
