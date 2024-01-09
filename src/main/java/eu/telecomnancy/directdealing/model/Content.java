package eu.telecomnancy.directdealing.model;

import javafx.scene.image.Image;

public abstract class Content {
    private int id;
    private static int currentId;
    private String title;
    private String category;
    private String description;
    private Image image;
    private Integer price;
    protected int type;

    public Content(String title, String category, String description, Image image) {
        this.id = currentId;
        currentId++;
        this.title = title;
        this.category = category;
        this.description = description;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public int getType() {
        return type;
    }
}
