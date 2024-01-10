package eu.telecomnancy.directdealing.views.proposal;


import eu.telecomnancy.directdealing.model.offer.Proposal;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

/**
 * ProposalCell class
 */
public class ProposalCell extends ListCell<Proposal>{
    /**
     * FXMLLoader
     */
    private FXMLLoader mLLoader;

    /**
     * update the item of the cell
     * @param proposal Proposal to update
     * @param b Boolean to know if the proposal is null
     */
    @Override
    protected void updateItem(Proposal proposal, boolean b) {
        super.updateItem(proposal, b);
        if (b || proposal == null) {
            setText(null);
            setGraphic(null);
        } else {
            if (mLLoader == null) {
                mLLoader = new FXMLLoader(getClass().getResource("/eu/telecomnancy/directdealing/views/proposal/proposal_cell.fxml"));
                mLLoader.setController(this);
                try {
                    mLLoader.load();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            // TODO

            setText(null);
            setGraphic(mLLoader.getRoot());
        }
    }
}
