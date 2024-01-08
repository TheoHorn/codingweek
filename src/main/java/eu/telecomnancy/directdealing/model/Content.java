package eu.telecomnancy.directdealing.model;

import javafx.scene.image.Image;

public abstract class Content {
    private String id;
    private String title;
    private String category;
    private String description;
    private Image image;

    public Content(String id, String title, String category, String description, Image image) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.description = description;
        this.image = image;
    }
}
