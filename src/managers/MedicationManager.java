package managers;

import entities.Medication;

import java.util.ArrayList;

public class MedicationManager {
    private String medicationName;
    private String prescribedFor;

    public MedicationManager(String medicationName, String prescribedFor) {
        this.medicationName = medicationName;
        this.prescribedFor = prescribedFor;
    }

    @Override
    public String toString(){
        return "Med name: " + this.medicationName + " | Prescribed for: " + this.prescribedFor;
    }



}
