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
        String title = this.titleTextField.getText();
        String description = this.descriptionTextArea.getText();
        // String category = this.categoryChoiceBox.getValue().toString();
        LocalDate selectedDate = this.startDatePicker.getValue();
        LocalDateTime startOfDay = selectedDate.atStartOfDay();
        Date startDate = Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
        selectedDate = this.endDatePicker.getValue();
        startOfDay = selectedDate.atStartOfDay();
        Date endDate = Date.from(startOfDay.atZone(ZoneId.systemDefault()).toInstant());
        boolean isRequest = this.request_button.isSelected();
        double price = Double.parseDouble(this.priceTextField.getText());

        if (title.isEmpty() || description.isEmpty() || startDate == null || endDate == null || price == 0){
            System.out.println("Veuillez remplir tous les champs");
        } else {
            if (isRequest) {
                Service service = new Service(title, "", description, null, price);

                Request request = new Request((User) Application.getInstance().getCurrentUser(), service, new Slot(startDate, endDate,0), true);
                OfferManager.addRequest(request);
            } else {
                Service service = new Service(title, "", description, null, price);
                Proposal proposal = new Proposal((User) Application.getInstance().getCurrentUser(), service, new Slot(startDate, endDate,0), false);
                OfferManager.addProposal(proposal);
            }
        }
    }
}
