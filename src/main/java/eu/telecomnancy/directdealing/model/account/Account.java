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

    /**
     * boolean to know if the user is sleeping
     */
    boolean isSleeping;

    /**
     * Balance of the account
     */
    double balance;
    /**
     * City of the account
     */
    private String city;
    /**
     * Address of the account
     */
    private String address;

    /**
     * Constructor of the account
     * @param lastName Last name of the account
     * @param firstName First name of the account
     * @param email Email of the account
     * @param password Password of the account
     */
    public Account(String lastName, String firstName, String email,String password,Boolean isSleeping, double balance, String city, String address) throws NoSuchAlgorithmException, InvalidKeySpecException {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.isSleeping = isSleeping;
        this.balance = balance;
        this.city = city;
        this.address = address;
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

    public void setCity(String city) {
    	this.city = city;
    }

    public void setAddress(String address) {
    	this.address = address;
    }

    public String getPassword() {
    	return password;
    }
    public void setPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
    	this.password = generateStrongPasswordHash(password);
    }

    public abstract boolean isSleeping();

    public abstract void updateSleeping(boolean isSleeping);

    public abstract double getBalance();

    public boolean equals(Account account){
        if (account ==null){
            return false;
        }
        return this.getEmail().equals(account.getEmail());
    }

    public String getAddress() {
        return address;
    }

    public String getCity(){
        return city;
    }

    public String getLocalisation() {
        return city + ", " + address;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
