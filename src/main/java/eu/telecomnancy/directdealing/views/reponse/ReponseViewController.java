package eu.telecomnancy.directdealing.views.reponse;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.demande.Demande;
import eu.telecomnancy.directdealing.model.Observer;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ReponseViewController implements Observer {

    @FXML
    protected ListView<Demande> answersListView;
    private final Application app;

    /**
     * Constructor of reponse view controller
     */
    public ReponseViewController() {
        this.app = Application.getInstance();
        this.app.addObserver(this);
    }
    /**
     * update method
     */
    @Override
    public void update() throws Exception {
        answersListView.getItems().clear();
        this.app.getDemandeManager().getAllMyAnswer().forEach(demande -> answersListView.getItems().add(demande));
        answersListView.setCellFactory(lv -> new ReponseCell());
    }
}
