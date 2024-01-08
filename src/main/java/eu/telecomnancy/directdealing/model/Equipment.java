package eu.telecomnancy.directdealing.model;

import javafx.scene.image.Image;

import java.util.Date;

public class Equipment extends Content{
    private Date returnDate;
    public Equipment(String id, String title, String category, String description, Image image, Date returnDate) {
        super(id, title, category, description, image);
        this.returnDate = returnDate;
    }
}
