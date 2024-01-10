package eu.telecomnancy.directdealing.model.account;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static eu.telecomnancy.directdealing.database.ReallyStrongSecuredPassword.generateStrongPasswordHash;

public abstract class Account {
    String lastName;
    String firstName;
    String email;
    String password;
    private boolean isAdministrator;

    public Account(String lastName, String firstName, String email,String password, boolean isAdministrator) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.isAdministrator = isAdministrator;
    }

    public void login(){}
    public void logout(){}

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
