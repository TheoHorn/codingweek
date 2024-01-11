package eu.telecomnancy.directdealing.views.admin.user;

import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.model.offer.Offer;
import eu.telecomnancy.directdealing.model.offer.Proposal;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.w3c.dom.events.MouseEvent;

import java.sql.SQLException;

import static eu.telecomnancy.directdealing.Main.app;

public class UserCell extends ListCell<User> {
    @FXML
    private Label mail_label;
    @FXML
    private Label last_name_label;
    @FXML
    private Label first_name_label;
    @FXML
    private ImageView user_image;
    /**
     * FXMLLoader
     */
    private FXMLLoader mLLoader;

    /**
     * update the item of the cell
     * @param user User to update
     * @param b Boolean to know if the user is null
     */
    @Override
    protected void updateItem(User user, boolean b) {
        super.updateItem(user, b);
        if (b || user == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/home/offer_cell.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            this.mail_label.setText(user.getEmail());
            this.last_name_label.setText(user.getLastName());
            this.first_name_label.setText(user.getFirstName());

        }
    }


    @FXML
    public void pressDelete(MouseEvent mouseEvent){
        // TODO
    }
}
