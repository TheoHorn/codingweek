package eu.telecomnancy.directdealing.model.content;

import javafx.scene.image.Image;

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
    public Equipment(String title, String category, String description, Image image, double price) throws SQLException {
        super(title, category, description, image, price);
        super.type = 1;
        super.setId(app.getContentDAO().save(this));
    }
}
