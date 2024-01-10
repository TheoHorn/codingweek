package eu.telecomnancy.directdealing.model.content;

import javafx.scene.image.Image;

import java.sql.SQLException;

import static eu.telecomnancy.directdealing.Main.app;

/**
 * Service class
 */
public class Service extends Content{
    /**
     * id of the service
     */
    private int id;

    /**
     * Constructor of the service
     * @param title Title of the service
     * @param category Category of the service
     * @param description Description of the service
     * @param image Image of the service
     * @param price Price of the service
     * @throws SQLException if the service is not save in the database
     */
    public Service(String title, String category, String description, Image image, double price) throws SQLException {
        super(title, category, description, image, price);
        super.type = 2;
        super.setId(app.getContentDAO().save(this));
    }
    public Service(int idService, String title, String category, String description, Image image, double price) {
        super(title, category, description, image, price);
        super.type = 2;
    }
}
