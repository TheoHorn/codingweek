package eu.telecomnancy.directdealing.views.accountlogin;

import eu.telecomnancy.directdealing.SceneController;
import eu.telecomnancy.directdealing.database.AccountManager;
import eu.telecomnancy.directdealing.Main;
import javafx.event.ActionEvent;
import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LoginView implements Observer {
    @FXML
    private TextField mail_TextField;
    @FXML
    private PasswordField password_TextField;
    @FXML
    private Label statusLabel;
    private Application app;

    public LoginView() {
        this.app = Application.getInstance();
    }

    @FXML
    public void switchToSign(MouseEvent event) throws Exception {
        SceneController sceneController = new SceneController();
        sceneController.switchToSignView(event);
    }

    @FXML
    public void loginButton(ActionEvent event) throws Exception {
        String localMail = mail_TextField.getText();
        String localPassword = password_TextField.getText();
        AccountManager accountManager = new AccountManager();
        app.setCurrentUser(accountManager.login(localMail, localPassword));
        if (app.getCurrentUser() != null) {
            SceneController sceneController = new SceneController();
            sceneController.switchToHome(event);
        } else {
            statusLabel.setText("Mot de passe ou email incorrect");
            System.out.println("Login failed");
        }
    }

    @Override
    public void update() {
        // TODO
    }

}
