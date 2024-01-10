package eu.telecomnancy.directdealing.model.account;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class User extends Account{

    double balance;

    boolean isSleeping;

    public User(String lastName, String firstName, String email, double balance, boolean isSleeping, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        super(lastName, firstName, email, password, false);
        this.balance = balance;
        this.isSleeping = isSleeping;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public boolean isSleeping() {
        return isSleeping;
    }

    public void setSleeping(boolean sleeping) {
        isSleeping = sleeping;
    }

    @Override
    public String toString() {
        return "User{" +
                "balance=" + balance +
                ", isSleeping=" + isSleeping +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
