package managers;

import entities.Owner;

public class OwnerManager extends Owner {
    private final static String SEP = " | ";
    public OwnerManager(String lastName, String firstName, String email, String password, String phoneNumber) {
        super(lastName, firstName, email, password, phoneNumber);
    }

    @Override
    public String toString(){
        return "Last name and first name: " + this.lastName + " " + this.firstName
                + SEP + "Email: " + this.email
                + SEP + "Phone number: " + this.phoneNumber;
    }
}
