package entities;

import java.sql.Date;
import java.util.List;

public class DoctorVisit extends Visit {
    private int doctor_id; // doctor_id(== employee_id)
    private int animal_id; // animal_id
    private String reasonForVisit;
    private String diagnosis;
    //private List<Medication> treatmentProposed;

    public DoctorVisit(Date visitDate, int doctor_id, int animal_id, String reasonForVisit, String diagnosis) {
        super(visitDate);
        this.doctor_id = doctor_id;
        this.animal_id = animal_id;
        this.reasonForVisit = reasonForVisit;
        this.diagnosis = diagnosis;
    }

    public int getDoctor_id() {
        return doctor_id;
    }

    public int getAnimal_id() {
        return animal_id;
    }

    public String getReasonForVisit() {
        return reasonForVisit;
    }

    public String getDiagnosis() {
        return diagnosis;
    }
}
