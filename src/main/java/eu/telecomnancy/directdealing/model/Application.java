package eu.telecomnancy.directdealing.model;

import java.util.ArrayList;
import java.util.List;

public class Application {
    private final Account currentUser;
    private final List<Offer> offers;
    private final List<Observer> observers;

    public Application(Account currentUser) {
        this.currentUser = currentUser;
        this.offers = new ArrayList<>();
        this.observers = new ArrayList<>();
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeAllObservers() {
        this.observers.clear();
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
