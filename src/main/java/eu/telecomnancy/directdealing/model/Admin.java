package eu.telecomnancy.directdealing.model;

public class Admin extends Account {
    public Admin(String lastName, String firstName, String email) {
        super(lastName, firstName, email);
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
