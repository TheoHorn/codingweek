package eu.telecomnancy.directdealing.model;

import javafx.scene.image.Image;

import java.util.Date;

public class Equipment extends Content{
    private Date returnDate;
    public Equipment(String title, String category, String description, Image image, Date returnDate) {
        super(title, category, description, image);
        this.returnDate = returnDate;
        super.type = 1;
    }

    public Date getReturnDate() {
        return returnDate;
    }
}
