package eu.telecomnancy.directdealing.views.admin.dispute;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.dispute.Dispute;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.views.admin.user.UserCell;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

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
        disputesListView.getItems().clear();


        this.app.getDisputes().forEach(dispute -> disputesListView.getItems().add(dispute));
        disputesListView.setCellFactory(lv -> new DisputeCell());
    }
}
