package entities;

import interfaces.IIdentifier;

public class Medication implements IIdentifier {
    private int medicationId;
    private String medicationName;
    private String prescribedFor;

    public Medication(String medicationName, String prescribedFor) {
        this.medicationName = medicationName;
        this.prescribedFor = prescribedFor;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public String getPrescribedFor() {
        return prescribedFor;
    }

    @Override
    public int getId() {
        return this.medicationId;
    }

    @Override
    public void setId(int id) {
        this.medicationId = id;
    }

    @Override
    public String toString(){
        return "ID: " + this.medicationId + " Med name: " + this.medicationName + " | Prescribed for: " + this.prescribedFor;
    }
}
