package eu.telecomnancy.directdealing.views.offer;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.account.Account;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.content.Content;
import eu.telecomnancy.directdealing.model.offer.Offer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public class OfferDisplayController implements Observer {

    private Application app;

    @FXML
    private Label title_label;

    @FXML
    private Label description_label;

    @FXML
    private Label price_label;

    @FXML
    private Label category_label;

    @FXML
    private Label disponibility_label;

    @FXML
    private Label owner_label;

    @FXML
    private Label type_label;

    @FXML
    private ImageView image_view;

    @FXML
    private Label location_label;


    public OfferDisplayController() {
        this.app = Application.getInstance();
        this.app.addObserver(this);
    }

    @FXML
    public void reservation() throws Exception {
        Content content = this.app.getContentDAO().get(this.app.getLastOffer().getIdContent());
        List<Slot> slots = this.app.getSlotDAO().get(this.app.getLastOffer().getIdOffer());
        double balance = app.getAccountDAO().get(app.getCurrentUser().getEmail()).getBalance();
        if (balance >= app.getContentDAO().get(app.getLastOffer().getIdContent()).getPrice()) {
            if (content.isEquipment() && slots.get(0).getStartTime() != null) {
                this.app.validateNewDemand(this.app.getLastOffer(), slots);
                this.app.getSceneController().switchToHome();
            } else {
                this.app.getSceneController().openReservationPopup();
            }
        } else {
            // TODO : display message
        }

    }

    @FXML
    public void contact() throws Exception {
        // TODO
    }

    @FXML
    public void proposeService() throws Exception {
        // TODO
    }
    @Override
    public void update() throws Exception {
        Offer offer = this.app.getLastOffer();
        Content content = app.getContentDAO().get(offer.getIdContent());
        List<Slot> slots = app.getSlotDAO().get(offer.getIdOffer());
        Account owner = app.getAccountDAO().get(offer.getMail());
        this.title_label.setText(content.getTitle());
        this.description_label.setText(content.getDescription());
        this.image_view.setImage(new Image(content.getImage().toURI().toString()));
        this.price_label.setText(String.valueOf(content.getPrice()));
        this.category_label.setText(content.getCategory());
        // this.disponibility_label.setText(slot.getDisponibility());
        this.owner_label.setText(owner.getFirstName()+" "+owner.getLastName());
        if (offer.isRequest()){
            this.type_label.setText("Request");
        } else {
            this.type_label.setText("Proposal");
        }
        //this.image_view.setImage(new Image(content.getImage().impl_getUrl()));
        //this.location_label.setText();

    }
}
