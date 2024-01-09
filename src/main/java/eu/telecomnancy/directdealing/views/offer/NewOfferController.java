package eu.telecomnancy.directdealing.views.offer;

import eu.telecomnancy.directdealing.database.OfferManager;
import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.content.Service;
import eu.telecomnancy.directdealing.model.offer.Proposal;
import eu.telecomnancy.directdealing.model.offer.Request;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.w3c.dom.Text;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import java.util.Date;

public class NewOfferController implements Observer {
    @FXML
    private TextField priceTextField;
    @FXML
    private ChoiceBox categoryChoiceBox;
    @FXML
    private TextArea descriptionTextArea;
    @FXML
    private TextField titleTextField;
    @FXML
    private RadioButton request_button;
    @FXML
    private RadioButton proposal_button;
    @FXML
    private DatePicker startDatePicker;
    @FXML
    private DatePicker endDatePicker;
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

    @FXML
    public void pressValiderNewOffer(ActionEvent actionEvent) throws SQLException {
        boolean err = app.validateNewOffer(this.titleTextField.getText(), this.descriptionTextArea.getText(), " ", this.startDatePicker.getValue(), this.endDatePicker.getValue(),  this.request_button.isSelected(), Double.parseDouble(this.priceTextField.getText()));
        if (!err) {
            System.out.println("Une erreur est survenue");
        }
    }
}
