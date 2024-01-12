package eu.telecomnancy.directdealing.model.content;

import java.sql.SQLException;

import static eu.telecomnancy.directdealing.Main.app;

public class ContentManager {
    public void delete(Content content) throws SQLException {
        // Delete this content
        app.getContentDAO().delete(content.getIdContent());
    }
}
