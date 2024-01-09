package eu.telecomnancy.directdealing.model.account;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Admin extends Account {
    public Admin(String lastName, String firstName, String email, String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
        super(lastName, firstName, email, password);
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
