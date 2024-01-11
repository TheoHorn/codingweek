package eu.telecomnancy.directdealing.views.reponse;

import eu.telecomnancy.directdealing.model.Demande;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.model.offer.Proposal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.MenuButton;
import javafx.scene.image.Image;

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
    private ListCell<String> slotsListView;
    @FXML
    private MenuButton statusButton;
    @FXML
    private Button validateButton;
    /**
     * FXMLLoader
     */
    private FXMLLoader mLLoader;

    @Override
    protected void updateItem(Demande demande, boolean b) {
        // TODO
    }



}
