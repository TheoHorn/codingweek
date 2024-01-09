package eu.telecomnancy.directdealing.views.logview;

import eu.telecomnancy.directdealing.Main;
import eu.telecomnancy.directdealing.database.AccountManager;
import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.model.account.User;
import eu.telecomnancy.directdealing.SceneController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class SignInViewController implements Observer {
    @FXML
    private TextField mail_textfield;
    @FXML
    private PasswordField password_textfield;
    @FXML
    private TextField firstname_textfield;
    @FXML
    private TextField lastname_textfield;
    @FXML
    private PasswordField password_confirm_textfield;
    @FXML
    private Button creationButton;
    @FXML
    private Label statusLabel;
    private Application app;

    public SignInViewController() {
        this.app = Application.getInstance();
    }

    public void pressCreationButton(ActionEvent event) throws Exception {
        boolean err = app.signin(mail_textfield.getText(), password_textfield.getText(), firstname_textfield.getText(), lastname_textfield.getText(), password_confirm_textfield.getText());
        if (err) {
            statusLabel.setText("Une erreur est survenue");
        }
    }

    @FXML
    public void switchToLoginPage(MouseEvent mouseEvent) throws Exception {
        app.getSceneController().switchToLoginView();
    }

    @Override
    public void update() {
        // TODO
    }
}
