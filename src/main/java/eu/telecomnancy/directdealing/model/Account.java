package eu.telecomnancy.directdealing.model;

public abstract class Account {

    String lastName;
    String firstName;
    String email;

    public Account(String lastName, String firstName, String email) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
    }

    public void login(){}
    public void logout(){}

}
