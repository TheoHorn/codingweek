package eu.telecomnancy.directdealing.views.dispute;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.model.dispute.Dispute;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class DisputeView implements Observer {
    @FXML
    protected ListView<Dispute> disputesListView;
    private final Application app;


    /**
     * Constructor of reponse view controller
     */
    public DisputeView() {
        this.app = Application.getInstance();
        this.app.addObserver(this);
    }
    /**
     * update method
     */
    @Override
    public void update() throws Exception {
        disputesListView.getItems().clear();
        this.app.getDisputeManager().getMyRequests().forEach(dispute -> disputesListView.getItems().add(dispute));
        disputesListView.setCellFactory(lv -> new DisputeCell());
    }
}
