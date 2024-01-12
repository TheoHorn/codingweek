package eu.telecomnancy.directdealing.model.dispute;

import java.util.ArrayList;
import java.util.List;

import static eu.telecomnancy.directdealing.Main.app;

public class DisputeManager {
    public List<Dispute> getMyRequests() {
        List<Dispute> disputes = app.getDisputeDAO().get();
        List<Dispute> myRequests = new ArrayList<Dispute>();
        for(Dispute dispute : disputes) {
            if (dispute.getAttacker().equals(app.getCurrentUser().getEmail())){
            myRequests.add(dispute);
            }
        }
        return myRequests;
    }
}
