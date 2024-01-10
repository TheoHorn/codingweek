package eu.telecomnancy.directdealing.model.account;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static eu.telecomnancy.directdealing.database.ReallyStrongSecuredPassword.generateStrongPasswordHash;

/**
 * Account class
 */
public abstract class Account {
    /**
     * Last name of the account
     */
    String lastName;
    /**
     * First name of the account
     */
    String firstName;
    /**
     * Email of the account
     */
    String email;
    /**
     * Password of the account
     */
    String password;
    /**
     * Boolean to know if the account is an administrator
     */
    private boolean isAdministrator;

    /**
     * Constructor of the account
     * @param lastName Last name of the account
     * @param firstName First name of the account
     * @param email Email of the account
     * @param password Password of the account
     * @param isAdministrator Boolean to know if the account is an administrator
     */
    public Account(String lastName, String firstName, String email,String password, boolean isAdministrator) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.isAdministrator = isAdministrator;
    }

    public String getLastName() {
        return lastName;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
    	return password;
    }
    public void setPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
    	this.password = generateStrongPasswordHash(password);
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }
}
