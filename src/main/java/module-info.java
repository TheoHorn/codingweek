module eu.telecomnancy.directdealing {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens eu.telecomnancy.directdealing to javafx.fxml;
    exports eu.telecomnancy.directdealing;
}