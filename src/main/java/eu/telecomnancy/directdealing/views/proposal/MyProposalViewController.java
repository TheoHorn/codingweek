package eu.telecomnancy.directdealing.views.proposal;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;
import eu.telecomnancy.directdealing.model.offer.Proposal;
import eu.telecomnancy.directdealing.views.proposal.ProposalCell;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

/**
 * MyProposalViewController class
 */
public class MyProposalViewController implements Observer {
    /**
     * ListView for the proposals
     */
    @FXML
    private ListView<Proposal> proposalsListView;
    /**
     * Application instance
     */
    private Application app;

    /**
     * Constructor of my proposal view controller
     */
    public MyProposalViewController() {
        this.app = Application.getInstance();
        this.app.addObserver(this);
    }

    /**
     * update the view
     */
    @Override
    public void update() {
        System.out.println("update");
        proposalsListView.getItems().clear();
        this.app.getMyProposals().forEach(proposal -> proposalsListView.getItems().add(proposal));
        proposalsListView.setCellFactory(lv -> new ProposalCell());
    }
}
