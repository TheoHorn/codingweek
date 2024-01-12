package eu.telecomnancy.directdealing.views.logview;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * LogInViewController class
 */
public class LogInViewController implements Observer {
    /**
     * TextField for the mail
     */
    @FXML
    private TextField mail_TextField;
    /**
     * TextField for the password
     */
    @FXML
    private PasswordField password_TextField;
    /**
     * Label for the status
     */
    @FXML
    private Label statusLabel;
    /**
     * Application instance
     */
    private final Application app;

    /**
     * Constructor of the login view controller
     */
    public LogInViewController() {
        this.app = Application.getInstance();
    }

    /**
     * perform the switch to the sign view
     *
     * @throws Exception if the switch failed
     */
    @FXML
    public void switchToSign() throws Exception {
        app.getSceneController().switchToSignView();
    }

    /**
     * perform the login
     *
     * @throws Exception if the login failed
     */
    @FXML
    public void loginButton() throws Exception {
        try {
            app.login(mail_TextField.getText(), password_TextField.getText());
        } catch (Exception e) {
            statusLabel.setText(e.getMessage());
        }
    }

    @Override
    public void update() {
        // TODO
    }

}
