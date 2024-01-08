package eu.telecomnancy.directdealing.model;

public class User extends Account{

    float balance;

    boolean isSleeping;

    public User(String lastName, String firstName, String email, float balance, boolean isSleeping) {
        super(lastName, firstName, email);
        this.balance = balance;
        this.isSleeping = isSleeping;
    }
}
