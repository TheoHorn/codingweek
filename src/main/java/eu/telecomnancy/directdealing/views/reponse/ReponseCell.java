package eu.telecomnancy.directdealing.views.reponse;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Slot;
import eu.telecomnancy.directdealing.model.account.Account;
import eu.telecomnancy.directdealing.model.content.Content;
import eu.telecomnancy.directdealing.model.demande.Demande;
import eu.telecomnancy.directdealing.model.offer.Offer;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

import java.sql.SQLException;
import static eu.telecomnancy.directdealing.Main.app;

public class ReponseCell extends ListCell<Demande> {
    @FXML
    private Label type;
    @FXML
    private Label bien_serviceLabel;
    @FXML
    private Label titleLabel;
    @FXML
    private Label priceLabel;
    @FXML
    private Label category;
    @FXML
    private Label placeLabel;
    @FXML
    private Label answerType;
    @FXML
    private Label slotLabel;
    @FXML
    private ChoiceBox statusChoiceBox;
    @FXML
    private Button validateButton;
    @FXML
    private Button disputeButton;
    /**
     * FXMLLoader
     */
    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Demande demande, boolean b) {
        super.updateItem(demande, b);
        if (b || demande == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/reponse/reponse_cell.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            try {
                Slot slot = app.getSlotDAO().get(demande.getIdSlot(),false);
                Offer offer = app.getOfferDAO().get(slot.getIdOffer());
                Content content = app.getContentDAO().get(offer.getIdContent());
                if (offer.isRequest()) {
                    type.setText("Demande");
                } else {
                    type.setText("Offre");
                }
                if (content.isEquipment) {
                    bien_serviceLabel.setText("Bien");
                } else {
                    bien_serviceLabel.setText("Service");
                }
                titleLabel.setText(content.getTitle());
                priceLabel.setText(String.valueOf(content.getPrice()));
                category.setText(content.getCategory());
                placeLabel.setText(app.getCurrentUser().getLocalisation());
                Account demandeur = app.getAccountDAO().get(demande.getMail());
                answerType.setText(demandeur.getFirstName() + " " + demandeur.getLastName() + " est intéressé par votre offre");
                slotLabel.setText(slot.toString());
                switch (demande.getStatus()) {
                    case 0:
                        statusChoiceBox.setValue("En attente");
                        break;
                    case 1:
                        statusChoiceBox.setValue("Accepter");
                        validateButton.setVisible(false);
                        validateButton.setDisable(true);
                        disputeButton.setVisible(true);
                        break;
                    case 2:
                        statusChoiceBox.setValue("Refuser");
                        validateButton.setVisible(false);
                        validateButton.setDisable(true);
                        break;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            setText(null);
            setGraphic(mLLoader.getRoot());
        }

    }

    public void saveStatus() throws Exception {

        Application.getInstance().setLastDemand(getItem());
        app.saveDemandeStatus(statusChoiceBox.getValue().toString());
    }

    public void porterReclamantion() throws Exception {
        Application.getInstance().setLastDemand(getItem());
        app.getSceneController().openDisputePopup(app.getCurrentUser().getEmail(), getItem().getMail());
    }



}
