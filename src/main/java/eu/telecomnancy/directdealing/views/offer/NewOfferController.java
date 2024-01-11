package eu.telecomnancy.directdealing.views.offer;

import com.dlsc.gemsfx.daterange.DateRange;
import com.dlsc.gemsfx.daterange.DateRangePicker;
import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.model.Slot;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    private RadioButton isRecurrent;
    @FXML
    private RadioButton isNotRecurrent;
    @FXML
    private TextField recurrency;
    @FXML
    private DateRangePicker dateRangePicker;
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
    private final ObservableList<Slot> tempSlots;

    /**
     * Constructor of the new offer view controller
     */
    public NewOfferController() {
        this.app = Application.getInstance();
        this.app.addObserver(this);
        this.tempSlots = FXCollections.observableArrayList();
    }

    @FXML
    public void initialize() {
        this.tempSlots.clear();
        this.slotListView.setItems(this.tempSlots);
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
        try {
            if (this.isService.isSelected() && this.isRecurrent.isSelected()) {
                app.validateNewOffer(this.titleTextField.getText(), this.descriptionTextArea.getText(), (String) this.categoryChoiceBox.getValue(), this.request_button.isSelected(), Double.parseDouble(this.priceTextField.getText()), this.image, new ArrayList<>(this.tempSlots));
                this.tempSlots.clear();
            } else if (this.isService.isSelected()) {
                DateRange dateRange = this.dateRangePicker.getValue();
                LocalDateTime tmp = dateRange.getStartDate().atStartOfDay();
                Date startDate = Date.from(tmp.atZone(ZoneId.systemDefault()).toInstant());
                tmp = dateRange.getEndDate().atStartOfDay();
                Date endDate = Date.from(tmp.atZone(ZoneId.systemDefault()).toInstant());
                List<Slot> slots = new ArrayList<>();
                slots.add(new Slot(startDate, endDate, 0, -1));
                app.validateNewOffer(this.titleTextField.getText(), this.descriptionTextArea.getText(), (String) this.categoryChoiceBox.getValue(), this.request_button.isSelected(), Double.parseDouble(this.priceTextField.getText()), this.image, slots);
            } else {
                app.validateNewOffer(this.titleTextField.getText(), this.descriptionTextArea.getText(), (String) this.categoryChoiceBox.getValue(), this.request_button.isSelected(), Double.parseDouble(this.priceTextField.getText()), this.image, this.returnDatePicker.getValue());
            }
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
        this.isRecurrent.setVisible(true);
        this.isRecurrent.setSelected(false);
        this.isNotRecurrent.setVisible(true);
        this.isNotRecurrent.setSelected(true);
        this.recurrency.setVisible(false);
        this.dateRangePicker.setVisible(true);
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
        this.isRecurrent.setVisible(false);
        this.isNotRecurrent.setVisible(false);
        this.recurrency.setVisible(false);
        this.dateRangePicker.setVisible(false);
        this.slotAddButton.setVisible(false);
        this.slotLabel.setVisible(false);
        this.slotListView.setVisible(false);
    }

    @FXML void pressRecurrent() {
        this.isRecurrent.setSelected(true);
        this.isNotRecurrent.setSelected(false);
        this.recurrency.setVisible(true);
        this.dateRangePicker.setVisible(true);
        this.slotAddButton.setVisible(true);
        this.slotLabel.setVisible(true);
        this.slotListView.setVisible(true);
    }

    @FXML void pressNotRecurrent() {
        this.isRecurrent.setSelected(false);
        this.isNotRecurrent.setSelected(true);
        this.recurrency.setVisible(false);
        this.dateRangePicker.setVisible(true);
        this.slotAddButton.setVisible(false);
        this.slotLabel.setVisible(false);
        this.slotListView.setVisible(false);
    }

    @FXML void addSlot() throws SQLException {
        DateRange dateRange = this.dateRangePicker.getValue();
        if (this.recurrency.getText().isEmpty()) {
            this.errorLabel.setVisible(true);
            this.errorLabel.setText("Veuillez sélectionner une date ET une récurrence");
            return;
        }
        LocalDateTime tmp = dateRange.getStartDate().atStartOfDay();
        Date startDate = Date.from(tmp.atZone(ZoneId.systemDefault()).toInstant());
        tmp = dateRange.getEndDate().atStartOfDay();
        Date endDate = Date.from(tmp.atZone(ZoneId.systemDefault()).toInstant());
        this.tempSlots.add(new Slot(startDate, endDate, Integer.parseInt(this.recurrency.getText()), -1));
    }

    @Override
    public void update() {
        // TODO
    }
}