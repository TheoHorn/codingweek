package eu.telecomnancy.directdealing.views.offer;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;

public class NewOfferController implements Observer {
    @FXML
    private RadioButton request_button;
    @FXML
    private RadioButton proposal_button;
    private Application app;
    public NewOfferController() {
        this.app = Application.getInstance();
    }

    @Override
    public void update() {
        // TODO
    }

    @FXML
    public void pressRequest(ActionEvent actionEvent) {
        this.proposal_button.setSelected(false);
        this.request_button.setSelected(true);
    }

    @FXML
    public void pressProposal(ActionEvent actionEvent) {
        this.request_button.setSelected(false);
        this.proposal_button.setSelected(true);
    }
}
