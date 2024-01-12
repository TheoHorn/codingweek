package eu.telecomnancy.directdealing.views.offer;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.account.Account;
import eu.telecomnancy.directdealing.model.content.Content;
import eu.telecomnancy.directdealing.model.offer.Offer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.List;

public class OfferDisplayController implements Observer {

    private final Application app;

    @FXML
    private Label title_label;

    @FXML
    private Label description_label;

    @FXML
    private Label price_label;

    @FXML
    private Label category_label;

    @FXML
    private ListView<String> dispoListView;

    @FXML
    private Label owner_label;

    @FXML
    private Label type_label;

    @FXML
    private ImageView image_view;

    @FXML
    private Label location_label;

    @FXML
    private Button resa_button;

    @FXML
    private Label resa_label;


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
        List<Slot> slots = this.app.getSlotDAO().get(offer.getIdOffer());
        Content content = app.getContentDAO().get(offer.getIdContent());
        Account owner = app.getAccountDAO().get(offer.getMail());
        this.title_label.setText(content.getTitle());
        this.description_label.setText(content.getDescription());
        this.image_view.setImage(new Image(content.getImage().toURI().toString()));
        this.price_label.setText(String.valueOf(content.getPrice()));
        this.category_label.setText(content.getCategory());
        this.location_label.setText(content.getLocalisation());
        // this.disponibility_label.setText(slot.getDisponibility());
        this.owner_label.setText(owner.getFirstName()+" "+owner.getLastName());
        if (offer.isRequest()){
            this.type_label.setText("Requête :");
        } else {
            this.type_label.setText("Proposition :");
        }
        //this.image_view.setImage(new Image(content.getImage().impl_getUrl()));
        //this.location_label.setText();
        this.dispoListView.getItems().clear();
        for (Slot slot : slots){
            this.dispoListView.getItems().add(slot.toString());
        }
        double balance = app.getAccountDAO().get(app.getCurrentUser().getEmail()).getBalance();
        double prix = app.getContentDAO().get(app.getLastOffer().getIdContent()).getPrice();
        if (balance >= prix){
            this.resa_button.setDisable(false);
            this.resa_label.setVisible(false);
        } else {
            this.resa_button.setDisable(true);
            this.resa_label.setVisible(true);
            double diff = prix - balance;
            this.resa_label.setText("Il vous manque "+diff+" florains pour pouvoir réserver.");
        }


    }
}
