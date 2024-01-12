package eu.telecomnancy.directdealing.model.account;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Admin class
 */
public class Admin extends Account {

    /**
     * Constructor of the admin
     * @param lastName Last name of the admin
     * @param firstName First name of the admin
     * @param email Email of the admin
     * @param password Password of the admin
     */
    public Admin(String lastName, String firstName, String email, String password, String city, String address) throws NoSuchAlgorithmException, InvalidKeySpecException {
        super(lastName, firstName, email, password, false, 0, city, address);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean isSleeping() {
        // Admins are never sleeping
        return false;
    }

    @Override
    public void updateSleeping(boolean isSleeping) {
        // Admins can't sleep
    }

    @Override
    public double getBalance() {
        // Admins have no balance
        return 0;
    }
}
