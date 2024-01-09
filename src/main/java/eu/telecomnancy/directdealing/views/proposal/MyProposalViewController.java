package eu.telecomnancy.directdealing.views.proposal;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.model.offer.Proposal;
import eu.telecomnancy.directdealing.views.proposal.ProposalCell;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class MyProposalViewController implements Observer {
    @FXML
    private ListView<Proposal> proposalsListView;
    private Application app;

    public MyProposalViewController() {
        this.app = Application.getInstance();
        this.app.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("update");
        proposalsListView.getItems().clear();
        this.app.getMyProposals().forEach(proposal -> proposalsListView.getItems().add(proposal));
        proposalsListView.setCellFactory(lv -> new ProposalCell());
    }
}
