package eu.telecomnancy.directdealing.views.logview;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * SignInViewController class
 */
public class SignInViewController implements Observer {
    /**
     * TextField for the mail
     */
    @FXML
    private TextField mail_textfield;
    /**
     * TextField for the password
     */
    @FXML
    private PasswordField password_textfield;
    /**
     * TextField for the firstname
     */
    @FXML
    private TextField firstname_textfield;
    /**
     * TextField for the lastname
     */
    @FXML
    private TextField lastname_textfield;
    /**
     * TextField for the city
     */
    @FXML
    private TextField city_textfield;
    /**
     * Textfield for the address
     */
    @FXML
    private TextField address_textfield;
    /**
     * TextField for the password confirmation
     */
    @FXML
    private PasswordField password_confirm_textfield;
    /**
     * Button for the creation
     */
    @FXML
    private Button creationButton;
    /**
     * Label for the status
     */
    @FXML
    private Label statusLabel;
    /**
     * Application instance
     */
    private Application app;

    /**
     * Constructor of the sign in view controller
     */
    public SignInViewController() {
        this.app = Application.getInstance();
    }

    /**
     * perform the creation
     * @param event the event that trigger the creation
     * @throws Exception if the creation failed
     */
    public void pressCreationButton(ActionEvent event) throws Exception {
        try {
            app.signin(mail_textfield.getText(), password_textfield.getText(), firstname_textfield.getText(), lastname_textfield.getText(), password_confirm_textfield.getText(), city_textfield.getText(), address_textfield.getText());
        } catch (Exception e) {
            statusLabel.setText(e.getMessage());
        }

    }

    /**
     * perform the switch to the login view
     * @param mouseEvent the event that trigger the switch
     * @throws Exception if the switch failed
     */
    @FXML
    public void switchToLoginPage(MouseEvent mouseEvent) throws Exception {
        app.getSceneController().switchToLoginView();
    }

    @Override
    public void update() {
        // TODO
    }
}
