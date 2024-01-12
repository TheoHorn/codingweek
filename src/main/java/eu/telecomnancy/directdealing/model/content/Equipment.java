package eu.telecomnancy.directdealing.model.content;

import java.io.File;
import java.sql.SQLException;

import static eu.telecomnancy.directdealing.Main.app;

public class Equipment extends Content{
    /**
     * Constructor of the equipment
     * @param title Title of the equipment
     * @param category Category of the equipment
     * @param description Description of the equipment
     * @param image Image of the equipment
     * @param price Price of the equipment
     * @throws SQLException if the equipment is not save in the database
     */
    public Equipment(String title, String category, String description, File image, double price, String localisation) throws SQLException {
        super(title, category, description, image, price, localisation);
        super.isEquipment = true;
        super.setIdContent(app.getContentDAO().save(this));
    }
    public Equipment(int idContent, String title, String category, String description, File image, double price, String localisation) {
        super(title, category, description, image, price, localisation);
        super.isEquipment = true;
        super.setIdContent(idContent);
    }
}
