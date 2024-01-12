package eu.telecomnancy.directdealing.views.profile;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.SQLException;

/**
 * ProfileViewController class
 */
public class ProfileViewController implements Observer {

    /**
     * TextField for the name
     */
    @FXML
    private TextField name_field;
    /**
     * TextField for the surname
     */
    @FXML
    private TextField surname_field;
    /**
     * Label for the email
     */
    @FXML
    private Label email_field;

    /**
     * TextField for the city
     */
    @FXML
    private TextField city_field;

    /**
     * TextField for the address
     */
    @FXML
    private TextField address_field;

    /**
     * PasswordField for the old password
     */
    @FXML
    private PasswordField old_password_field;
    /**
     * PasswordField for the new password
     */
    @FXML
    private PasswordField new_password_field;
    /**
     * PasswordField for the confirmation password
     */
    @FXML
    private PasswordField confirm_password_field;
    /**
     * Label that indicates if the modification of the password is a success
     */
    @FXML
    private Label modify_password_label;

    /**
     * Label that indicates if the modification of the profile info is a success
     */
    @FXML
    private Label modify_info_label;

    /**
     * Label that indicates if the user is sleeping
     */
    @FXML
    private Label sleeping_label;

    /**
     * Button that allows to update the sleeping status
     */
    @FXML
    private Button sleeping_button;


    /**
     * boolean that indicates if the modification of the profile info is a failure
     */
    private boolean isFailed;

    /**
     * Application instance
     */
    private final Application app;

    /**
     * Constructor of the profile view controller
     */
    public ProfileViewController() {
        this.app = Application.getInstance();
        this.app.addObserver(this);
    }

    /**
     * perform the update of the profile info
     * @throws Exception if the update failed
     */
    @FXML
    public void updateProfile() throws Exception {
        isFailed = this.app.updateCurrentAccount(this.name_field.getText(), this.surname_field.getText(),this.city_field.getText(), this.address_field.getText());
    }

    /**
     * perform the update of the password
     * @throws Exception if the update failed
     */
    @FXML
    public void updatePassword() throws Exception {
        isFailed = this.app.updateCurrentPassword(this.old_password_field.getText(), this.new_password_field.getText(), this.confirm_password_field.getText());
    }

    /**
     * perform the disconnection
     * @throws Exception if the disconnection failed
     */
    @FXML
    public void disconnection() throws Exception {
        this.app.deleteCurrentUser();
        this.app.getSceneController().switchToLoginView();
    }

    @FXML
    public void sleeping_update() throws Exception {
        this.app.updateCurrentUserSleeping(!this.app.getCurrentUser().isSleeping());
        this.update();
    }

    /**
     * update the view
     */
    @Override
    public void update() throws SQLException {
        if (!isFailed){
            this.modify_password_label.setVisible(true);
            this.modify_info_label.setVisible(true);
            isFailed = false;
        }else{
            this.modify_password_label.setVisible(false);
            this.modify_info_label.setVisible(false);
        }
        this.sleeping_button.setText(this.app.getCurrentUser().isSleeping() ? "Désactiver" : "Activer");
        this.name_field.setText(this.app.getCurrentUser().getFirstName());
        this.surname_field.setText(this.app.getCurrentUser().getLastName());
        this.email_field.setText(this.app.getCurrentUser().getEmail());
        this.city_field.setText(this.app.getCurrentUser().getCity());
        this.address_field.setText(this.app.getCurrentUser().getAddress());
        this.modify_info_label.setVisible(false);
        this.modify_password_label.setVisible(false);
        this.sleeping_label.setText(this.app.getCurrentUser().isSleeping() ? "Actif" : "Inactif");
    }
}