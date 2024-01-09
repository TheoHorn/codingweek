module eu.telecomnancy.directdealing {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens eu.telecomnancy.directdealing to javafx.fxml;
    opens eu.telecomnancy.directdealing.views.logview to javafx.fxml;
    exports eu.telecomnancy.directdealing;
    exports eu.telecomnancy.directdealing.views.logview;
    exports eu.telecomnancy.directdealing.model;
}