package eu.telecomnancy.directdealing.model;

public class User extends Account{

    double balance;

    boolean isSleeping;

    public User(String lastName, String firstName, String email, double balance, boolean isSleeping) {
        super(lastName, firstName, email);
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
