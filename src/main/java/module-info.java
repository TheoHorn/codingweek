module eu.telecomnancy.directdealing {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.dlsc.gemsfx;


    opens eu.telecomnancy.directdealing to javafx.fxml;
    opens eu.telecomnancy.directdealing.views.logview to javafx.fxml;
    opens eu.telecomnancy.directdealing.views.navbar to javafx.fxml;
    opens eu.telecomnancy.directdealing.views.profile to javafx.fxml;
    opens eu.telecomnancy.directdealing.views.offer to javafx.fxml;
    opens eu.telecomnancy.directdealing.views.home to javafx.fxml;
    opens eu.telecomnancy.directdealing.views.proposal to javafx.fxml;
    opens eu.telecomnancy.directdealing.views.mydemands to javafx.fxml;
    opens eu.telecomnancy.directdealing.views.messaging to javafx.fxml;
    exports eu.telecomnancy.directdealing.views.offer to javafx.fxml;
    exports eu.telecomnancy.directdealing.views.home to javafx.fxml;
    exports eu.telecomnancy.directdealing;
    exports eu.telecomnancy.directdealing.views.logview;
    exports eu.telecomnancy.directdealing.model;
    exports eu.telecomnancy.directdealing.model.account;
    exports eu.telecomnancy.directdealing.model.content;
    exports eu.telecomnancy.directdealing.model.offer;
    exports eu.telecomnancy.directdealing.views.navbar;
    exports eu.telecomnancy.directdealing.views.profile;
    exports eu.telecomnancy.directdealing.views.proposal;
    exports eu.telecomnancy.directdealing.views.mydemands;
    exports eu.telecomnancy.directdealing.model.demande;
    exports eu.telecomnancy.directdealing.views.messaging;
    exports eu.telecomnancy.directdealing.model.messaging;
}