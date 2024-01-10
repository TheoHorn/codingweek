package eu.telecomnancy.directdealing.views.offer;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import com.dlsc.gemsfx.DurationPicker;

import java.io.File;
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
    private RadioButton isService;
    @FXML
    private RadioButton isEquipment;
    @FXML
    private RadioButton proposal_button;
    @FXML
    private Label returnDateLabel;
    @FXML
    private DatePicker returnDatePicker;
    @FXML
    private Label serviceDurationLabel;
    @FXML
    private DurationPicker serviceDurationPicker;
    @FXML
    private RadioButton isRecurrent;
    @FXML
    private RadioButton isNotRecurrent;
    @FXML
    private Label everyLabel;
    @FXML
    private TextField recurrencyTextField;
    @FXML
    private Label linkLabel;
    @FXML
    private DatePicker firstDayPicker;
    @FXML
    private Label toLabel;
    @FXML
    private DatePicker lastDayPicker;
    @FXML
    private Button slotAddButton;
    @FXML
    private Label slotLabel;
    @FXML
    private ListView slotListView;
    /**
     * Label for errors
     */
    @FXML
    private Label errorLabel;
    /**
     * Application instance
     */
    private Application app;
    private File image;

    /**
     * Constructor of the new offer view controller
     */
    public NewOfferController() {
        this.app = Application.getInstance();
        this.app.addObserver(this);
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

    @FXML
    public void pickImage() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir une image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg", "*.gif"));
        File selectedFile = fileChooser.showOpenDialog(app.getSceneController().getStage());
        if (selectedFile != null) {
            this.image = selectedFile;
        }
    }

    /**
     * allows to validate the new offer
     * @param actionEvent the event that trigger the validation
     * @throws SQLException if the validation failed
     */
    @FXML
    public void pressValiderNewOffer(ActionEvent actionEvent) {
        System.out.println(this.categoryChoiceBox.getValue() == null);
        try {
            app.validateNewOffer(this.titleTextField.getText(), this.descriptionTextArea.getText(), (String)this.categoryChoiceBox.getValue(), null, null,  this.request_button.isSelected(), Double.parseDouble(this.priceTextField.getText()), this.image);
        } catch(NumberFormatException e) {
            this.errorLabel.setVisible(true);
            this.errorLabel.setText("Le prix doit être un nombre");
        }catch (Exception e) {
            this.errorLabel.setVisible(true);
            this.errorLabel.setText(e.getMessage());
        }
    }

    @FXML
    public void pressService() {
        this.isService.setSelected(true);
        this.isEquipment.setSelected(false);
        this.returnDateLabel.setVisible(false);
        this.returnDatePicker.setVisible(false);
        this.serviceDurationLabel.setVisible(true);
        this.serviceDurationPicker.setVisible(true);
        this.isRecurrent.setVisible(true);
        this.isRecurrent.setSelected(false);
        this.isNotRecurrent.setVisible(true);
        this.isNotRecurrent.setSelected(true);
        this.everyLabel.setVisible(false);
        this.recurrencyTextField.setVisible(false);
        this.linkLabel.setVisible(true);
        this.linkLabel.setText("du");
        this.firstDayPicker.setVisible(true);
        this.toLabel.setVisible(true);
        this.lastDayPicker.setVisible(true);
        this.slotAddButton.setVisible(false);
        this.slotLabel.setVisible(false);
        this.slotListView.setVisible(false);
    }

    @FXML
    public void pressEquipment() {
        this.isService.setSelected(false);
        this.isEquipment.setSelected(true);
        this.returnDateLabel.setVisible(true);
        this.returnDatePicker.setVisible(true);
        this.serviceDurationLabel.setVisible(false);
        this.serviceDurationPicker.setVisible(false);
        this.isRecurrent.setVisible(false);
        this.isNotRecurrent.setVisible(false);
        this.everyLabel.setVisible(false);
        this.recurrencyTextField.setVisible(false);
        this.linkLabel.setVisible(false);
        this.firstDayPicker.setVisible(false);
        this.toLabel.setVisible(false);
        this.lastDayPicker.setVisible(false);
        this.slotAddButton.setVisible(false);
        this.slotLabel.setVisible(false);
        this.slotListView.setVisible(false);
    }

    @FXML void pressRecurrent() {
    	this.isRecurrent.setSelected(true);
    	this.isNotRecurrent.setSelected(false);
        this.everyLabel.setVisible(true);
        this.recurrencyTextField.setVisible(true);
        this.linkLabel.setVisible(true);
        this.linkLabel.setText("jours du");
        this.firstDayPicker.setVisible(true);
        this.toLabel.setVisible(true);
        this.lastDayPicker.setVisible(true);
        this.slotAddButton.setVisible(true);
        this.slotLabel.setVisible(true);
        this.slotListView.setVisible(true);
    }

    @FXML void pressNotRecurrent() {
    	this.isRecurrent.setSelected(false);
    	this.isNotRecurrent.setSelected(true);
        this.everyLabel.setVisible(false);
        this.recurrencyTextField.setVisible(false);
        this.linkLabel.setVisible(true);
        this.linkLabel.setText("du");
        this.firstDayPicker.setVisible(true);
        this.toLabel.setVisible(true);
        this.lastDayPicker.setVisible(true);
        this.slotAddButton.setVisible(false);
        this.slotLabel.setVisible(false);
        this.slotListView.setVisible(false);
    }

    @Override
    public void update() {
        // TODO
    }
}
