package eu.telecomnancy.directdealing.model.content;

import javafx.scene.image.Image;

import java.io.File;
import java.sql.SQLException;

import static eu.telecomnancy.directdealing.Main.app;

/**
 * Service class
 */
public class Service extends Content{

    /**
     * Constructor of the service
     * @param title Title of the service
     * @param category Category of the service
     * @param description Description of the service
     * @param image Image of the service
     * @param price Price of the service
     * @throws SQLException if the service is not saved in the database
     */
    public Service(String title, String category, String description, File image, double price) throws SQLException {
        super(title, category, description, image, price);
        super.isEquipment = false;
        super.setIdContent(app.getContentDAO().save(this));
    }
    public Service(int idService, String title, String category, String description, File image, double price) {
        super(title, category, description, image, price);
        super.isEquipment = false;
        super.setIdContent(idService);
    }
}
