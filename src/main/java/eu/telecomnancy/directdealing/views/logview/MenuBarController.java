package eu.telecomnancy.directdealing.views.logview;

import eu.telecomnancy.directdealing.model.Application;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;
import java.sql.SQLException;

/**
 * MenuBarController class
 */
public class MenuBarController {
    /**
     * Application instance
     */
    private Application app;

    /**
     * Constructor of the menu bar controller
     */
    public MenuBarController() {
        this.app = Application.getInstance();
    }

    /**
     * create a new database
     * @throws SQLException if the database is not created
     */
    @FXML
    public void newDB() throws SQLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Create new database");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("SQLite DB", "*.db"));
        File selectedFile = fileChooser.showSaveDialog(app.getSceneController().getStage());
        if (selectedFile != null) {
            app.createNewDatabaseFile(selectedFile);
        }
    }

    /**
     * open a database
     * @throws SQLException if the database is not opened
     */
    @FXML
    public void openDB() throws SQLException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open database");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("SQLite DB", "*.db"));
        File selectedFile = fileChooser.showOpenDialog(app.getSceneController().getStage());
        if (selectedFile != null) {
            app.openDatabaseFile(selectedFile);
        }
    }
}
