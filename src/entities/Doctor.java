package entities;

import java.io.Serializable;

public class Doctor extends Employee {
    private String specialization;
    private int yearOfGraduation;

    public Doctor(String lastName, String firstName, String phoneNumber, String email, String password, String specialization, int yearOfGraduation) {
        super(lastName, firstName, phoneNumber, email, password);
        this.specialization = specialization;
        this.yearOfGraduation = yearOfGraduation;
    }

    public String getSpecialization() {
        return specialization;
    }

    public int getYearOfGraduation() {
        return yearOfGraduation;
    }
}
