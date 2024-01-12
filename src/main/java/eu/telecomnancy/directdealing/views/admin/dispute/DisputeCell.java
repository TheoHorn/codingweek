package eu.telecomnancy.directdealing.views.admin.dispute;


import eu.telecomnancy.directdealing.model.account.Account;
import eu.telecomnancy.directdealing.model.dispute.Dispute;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.ImageView;

import java.sql.SQLException;

import static eu.telecomnancy.directdealing.Main.app;

public class DisputeCell extends ListCell<Dispute> {
    @FXML
    private Label attacker_label;
    @FXML
    private Label defender_label;
    @FXML
    private Label owner_label;
    @FXML
    private Label message_label;
    @FXML
    private Label type_label;

    /**
     * FXMLLoader
     */
    private FXMLLoader mLLoader;

    /**
     * update the item of the cell
     * @param dispute Dispute to update
     * @param b Boolean to know if the user is null
     */
    @Override
    protected void updateItem(Dispute dispute, boolean b) {
        super.updateItem(dispute, b);
        if (b || dispute == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/admin/dispute/dispute_cell.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            this.attacker_label.setText(dispute.getAttacker());
            this.defender_label.setText(dispute.getDefender());
            try {
                if (dispute.isOwner(dispute.getAttacker())){
                    this.owner_label.setText(dispute.getAttacker());
                }
                else {
                    this.owner_label.setText(dispute.getDefender());
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            this.message_label.setText(dispute.getContent());
            try {
                if (dispute.getOffer().isRequest()){
                    this.type_label.setText("Demande");
                }
                else {
                    this.type_label.setText("Annonce");
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            setText(null);
            setGraphic(mLLoader.getRoot());
        }
    }


    @FXML
    public void pressAttacker() throws Exception {
        if (type_label.getText().equals("Demande")){
            if (owner_label.getText().equals(attacker_label.getText())){
                Account attacker = app.getAccountDAO().get(attacker_label.getText());
                attacker.setBalance(attacker.getBalance() + getItem().getContentObject().getPrice());
                app.getAccountDAO().save(attacker);
            }
        } else {
            if (owner_label.getText().equals(defender_label.getText())){
                Account attacker = app.getAccountDAO().get(attacker_label.getText());
                attacker.setBalance(attacker.getBalance() + getItem().getContentObject().getPrice());
                app.getAccountDAO().save(attacker);
            }
        }
        app.deleteDispute(getItem());
    }

    public void pressDefender() throws Exception {
        System.out.println("Attaquant, C'est mal !");
        app.deleteDispute(getItem());
    }
}
