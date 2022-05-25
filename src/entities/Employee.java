package entities;

import interfaces.IIdentifier;

public class Employee implements IIdentifier {

    protected int employeeId;
    protected String lastName;
    protected String firstName;
    protected String phoneNumber;
    protected String email;
    protected String password;
    protected boolean loggedIn;

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Employee(String lastName, String firstName, String phoneNumber, String email, String password) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
        this.loggedIn = false;
    }

    @Override
    public int getId() {
        return this.employeeId;
    }

    @Override
    public void setId(int id) {
        this.employeeId = id;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

}
