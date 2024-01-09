package eu.telecomnancy.directdealing.views.offer;

import eu.telecomnancy.directdealing.model.Application;
import eu.telecomnancy.directdealing.model.Observer;

public class NewOffer implements Observer {
    private Application app;
    public NewOffer() {
        this.app = Application.getInstance();
    }

    @Override
    public void update() {
        // TODO
    }
}
