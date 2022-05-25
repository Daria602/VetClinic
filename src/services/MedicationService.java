package services;

import entities.Medication;
import managers.MedicationManager;
import repos.MedicationRepo;

import java.util.ArrayList;

public class MedicationService {
    private MedicationRepo medicationRepo;

    public MedicationService(){
        this.medicationRepo = new MedicationRepo();
    }

    public boolean addNewMedication(Medication m){ return this.medicationRepo.insertNewMedication(m); }

    public boolean removeMedication(int id){ return this.medicationRepo.deleteMedicationById(id); }

    public ArrayList<Medication> getMedicationForSpecificAnimal(int id){ return this.medicationRepo.getAllMedicationsByAnimalId(id); }

    public ArrayList<MedicationManager> convertListOfMedication(ArrayList<Medication> medications){
        ArrayList<MedicationManager> result = new ArrayList<>();
        if (medications != null) {
            for (int i = 0; i < medications.size(); i++) {
                result.add(new MedicationManager(medications.get(i).getMedicationName(), medications.get(i).getPrescribedFor()));
            }
        }
        return result;
    }

    public boolean updateMedicationById(int medicationId, String field, String value){ return this.medicationRepo.updateMedicationById(medicationId, field, value); }

    public ArrayList<Medication> getAllMedication(){ return this.medicationRepo.getAllMedication(); }

}
