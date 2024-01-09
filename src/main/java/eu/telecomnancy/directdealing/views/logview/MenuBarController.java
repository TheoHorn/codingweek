package eu.telecomnancy.directdealing.views.logview;

import eu.telecomnancy.directdealing.model.Application;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import java.io.File;
import java.sql.SQLException;

public class MenuBarController {
    private Application app;

    public MenuBarController() {
        this.app = Application.getInstance();
    }

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
