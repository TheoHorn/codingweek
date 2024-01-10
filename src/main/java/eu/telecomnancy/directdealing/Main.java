package eu.telecomnancy.directdealing;

import eu.telecomnancy.directdealing.database.DatabaseAccess;
import eu.telecomnancy.directdealing.views.logview.LogInViewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Main class
 */
public class Main extends Application {
    /**
     * Application instance
     */
    public static eu.telecomnancy.directdealing.model.Application app;

    /**
     * stop the application
     * @throws SQLException
     */
    @Override
    public void stop() throws SQLException {
        DatabaseAccess.disconnectFromDatabase();
    }

    /**
     * start the application
     * @param stage the stage of the application
     * @throws IOException if the application is not started
     */
    @Override
    public void start(Stage stage) throws IOException {
        app = eu.telecomnancy.directdealing.model.Application.getInstance();
        app.setSceneController(new SceneController(stage));

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/eu/telecomnancy/directdealing/views/logview/login_view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * main function
     * @param args arguments of the main function
     */
    public static void main(String[] args) {
        launch();
    }
}