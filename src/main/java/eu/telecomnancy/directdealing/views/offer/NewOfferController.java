package eu.telecomnancy.directdealing.views.offer;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

/**
 * NewOfferController class
 */
public class NewOfferController implements Observer {
    /**
     * TextField for the price
     */
    @FXML
    private TextField priceTextField;
    /**
     * ChoiceBox for the category
     */
    @FXML
    private ChoiceBox categoryChoiceBox;
    /**
     * TextArea for the description
     */
    @FXML
    private TextArea descriptionTextArea;
    /**
     * TextField for the title
     */
    @FXML
    private TextField titleTextField;
    /**
     * RadioButton for the request
     */
    @FXML
    private RadioButton request_button;
    /**
     * RadioButton for the proposal
     */
    @FXML
    private RadioButton proposal_button;
    /**
     * DatePicker for the start date
     */
    @FXML
    private DatePicker startDatePicker;
    /**
     * DatePicker for the end date
     */
    @FXML
    private DatePicker endDatePicker;
    /**
     * Application instance
     */
    private Application app;

    /**
     * Constructor of the new offer view controller
     */
    public NewOfferController() {
        this.app = Application.getInstance();
    }

    @Override
    public void update() {
        // TODO
    }

    /**
     * allows to select the request button and unselect the proposal button
     * @param actionEvent the event that trigger the selection
     */
    @FXML
    public void pressRequest(ActionEvent actionEvent) {
        this.proposal_button.setSelected(false);
        this.request_button.setSelected(true);
    }

    /**
     * allows to select the proposal button and unselect the request button
     * @param actionEvent the event that trigger the selection
     */
    @FXML
    public void pressProposal(ActionEvent actionEvent) {
        this.request_button.setSelected(false);
        this.proposal_button.setSelected(true);
    }

    /**
     * allows to validate the new offer
     * @param actionEvent the event that trigger the validation
     * @throws SQLException if the validation failed
     */
    @FXML
    public void pressValiderNewOffer(ActionEvent actionEvent) throws SQLException {
        boolean err = app.validateNewOffer(this.titleTextField.getText(), this.descriptionTextArea.getText(), " ", this.startDatePicker.getValue(), this.endDatePicker.getValue(),  this.request_button.isSelected(), Double.parseDouble(this.priceTextField.getText()));
        if (!err) {
            System.out.println("Une erreur est survenue");
        }
    }
}
