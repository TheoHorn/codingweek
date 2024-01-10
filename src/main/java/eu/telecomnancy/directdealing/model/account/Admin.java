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
    public Admin(String lastName, String firstName, String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        super(lastName, firstName, email, password, true);
    }

    @Override
    public String toString() {
        return "Admin{" +
                "lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
