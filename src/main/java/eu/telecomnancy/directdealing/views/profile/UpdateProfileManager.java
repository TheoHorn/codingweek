package eu.telecomnancy.directdealing.views.profile;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class UpdateProfileManager implements Observer {

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

    private boolean isFailed;

    private Application app;

    public UpdateProfileManager() {
        this.app = Application.getInstance();
    }

    @FXML
    public void initialize() {
        this.name_field.setText(this.app.getCurrentUser().getFirstName());
        this.surname_field.setText(this.app.getCurrentUser().getLastName());
        this.email_field.setText(this.app.getCurrentUser().getEmail());
        this.modify_info_label.setVisible(false);
        this.modify_password_label.setVisible(false);
    }

    public void updateField(){
        if (!isFailed){
            this.modify_password_label.setVisible(true);
            this.modify_info_label.setVisible(true);
            isFailed = false;
        }else{
            this.modify_password_label.setVisible(false);
            this.modify_info_label.setVisible(false);
        }
    }

    @FXML
    public void updateProfile() throws Exception {
        isFailed = this.app.updateCurrentAccount(this.name_field.getText(), this.surname_field.getText());
        System.out.println(isFailed);
        this.updateField();
    }

    @FXML
    public void updatePassword() throws Exception {
        isFailed = this.app.updateCurrentPassword(this.old_password_field.getText(), this.new_password_field.getText(), this.confirm_password_field.getText());
        System.out.println(isFailed);
        this.updateField();
    }

    @FXML
    public void disconnection(){
        this.app.deleteCurrentUser();
    }

    @Override
    public void update() {
    }
}