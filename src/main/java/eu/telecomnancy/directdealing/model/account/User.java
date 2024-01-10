package eu.telecomnancy.directdealing.model.account;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class User extends Account{

    /**
     * balance of the user
     */
    double balance;

    /**
     * boolean to know if the user is sleeping
     */
    boolean isSleeping;

    /**
     * Constructor of the user
     * @param lastName Last name of the user
     * @param firstName First name of the user
     * @param email Email of the user
     * @param balance Balance of the user
     * @param isSleeping Boolean to know if the user is sleeping
     * @param password Password of the user
     * @throws NoSuchAlgorithmException if the algorithm is not correct
     * @throws InvalidKeySpecException if the key is not correct
     */
    public User(String lastName, String firstName, String email, double balance, boolean isSleeping, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        super(lastName, firstName, email, password, false);
        this.balance = balance;
        this.isSleeping = isSleeping;
        System.out.println("User created");
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
