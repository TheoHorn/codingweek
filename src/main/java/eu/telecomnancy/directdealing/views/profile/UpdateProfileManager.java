package eu.telecomnancy.directdealing.views.profile;

import eu.telecomnancy.directdealing.SceneController;
import eu.telecomnancy.directdealing.database.AccountManager;
import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.account.User;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.EventListener;

public class UpdateProfileManager {

    @FXML
    private TextField name_field;
    @FXML
    private TextField surname_field;

    @FXML
    private Label email_field;

    @FXML
    private PasswordField old_password_field;

    @FXML
    private PasswordField new_password_field;

    @FXML
    private PasswordField confirm_password_field;

    @FXML
    private Label modify_password_label;

    @FXML
    private Label modify_info_label;

    private boolean isfailed;

    private Application app;

    public UpdateProfileManager() {
        this.app = Application.getInstance();
    }

    @FXML
    public void initialize() {
        this.name_field.setText(this.app.getCurrentUser().getFirstName());
        this.surname_field.setText(this.app.getCurrentUser().getLastName());
        this.email_field.setText(this.app.getCurrentUser().getEmail());
        if (isfailed){
            this.modify_password_label.setVisible(true);
            this.modify_info_label.setVisible(true);
            isfailed = false;
        }else{
            this.modify_password_label.setVisible(false);
            this.modify_info_label.setVisible(false);
        }
    }

    @FXML
    public void updateProfile() throws Exception {
        String name = this.name_field.getText();
        String surname = this.surname_field.getText();
        if (!(name.isEmpty() || surname.isEmpty())) {
            this.app.getCurrentUser().setFirstName(name);
            this.app.getCurrentUser().setLastName(surname);
            isfailed = app.getAccountManager().updateAccountInfo(this.app.getCurrentUser());
        }
        if (isfailed) {
            app.getSceneController().switchToProfile();
        } else {
            app.getSceneController().switchToHome();
        }
    }

    @FXML
    public void updatePassword() throws Exception {
        String oldPassword = this.old_password_field.getText();
        String newPassword = this.new_password_field.getText();
        String confirmPassword = this.confirm_password_field.getText();
        if (newPassword.equals(confirmPassword)) {
                isfailed = app.getAccountManager().updatePasswordAccount(oldPassword, newPassword, this.app.getCurrentUser());
        }
        if (isfailed) {
            app.getSceneController().switchToProfile();
        } else {
            app.getSceneController().switchToHome();
        }
    }

    @FXML
    public void deconnexion() throws Exception {
        this.app.deleteCurrentUser();
        app.getSceneController().switchToLoginView();
    }

}