package entities;

import interfaces.IIdentifier;
import managers.AnimalManager;

import java.util.ArrayList;
import java.util.List;

public class Owner implements IIdentifier {
    private final static String SEP = " | ";
    private int ownerId;
    protected String lastName;
    protected String firstName;
    protected String email;
    private String password;
    protected String phoneNumber;
    private ArrayList<AnimalManager> pets;

    public Owner(String lastName, String firstName, String email, String password, String phoneNumber) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.pets = new ArrayList<>();
    }

    public ArrayList<AnimalManager> getPets() {
        return pets;
    }

    public void setPets(ArrayList<AnimalManager> pets) {
        this.pets = pets;
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

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public int getId() {
        return this.ownerId;
    }

    @Override
    public void setId(int id) {
        this.ownerId = id;
    }

    @Override
    public String toString(){
        return "ID: " + getId() + SEP +
                "Name: " + this.lastName + " " + this.firstName + SEP +
                "Email: " + this.email + SEP +
                "Phone number: " + this.phoneNumber;
    }
}
