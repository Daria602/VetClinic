import entities.Animal;
import entities.Doctor;
import entities.DoctorVisit;
import entities.Owner;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteToCSV {

    public void writeDoctors(FileWriter csvWriter, ArrayList<Doctor> doctors) throws IOException {
        csvWriter.append("id");
        csvWriter.append(",");
        csvWriter.append("lastname");
        csvWriter.append(",");
        csvWriter.append("firstname");
        csvWriter.append(",");
        csvWriter.append("phonenumber");
        csvWriter.append(",");
        csvWriter.append("email");
        csvWriter.append(",");
        csvWriter.append("password");
        csvWriter.append(",");
        csvWriter.append("specialization");
        csvWriter.append(",");
        csvWriter.append("yearOfGraduation");
        csvWriter.append("\n");

        for (int i = 0; i < doctors.size(); i++){
            Doctor currentDoctor = doctors.get(i);
            csvWriter.append(String.format("%d",currentDoctor.getId()));
            csvWriter.append(",");
            csvWriter.append(currentDoctor.getLastName());
            csvWriter.append(",");
            csvWriter.append(currentDoctor.getFirstName());
            csvWriter.append(",");
            csvWriter.append(currentDoctor.getPhoneNumber());
            csvWriter.append(",");
            csvWriter.append(currentDoctor.getEmail());
            csvWriter.append(",");
            csvWriter.append(currentDoctor.getPassword());
            csvWriter.append(",");
            csvWriter.append(currentDoctor.getSpecialization());
            csvWriter.append(",");
            csvWriter.append(String.format("%d",currentDoctor.getYearOfGraduation()));
            csvWriter.append("\n");

        }

        csvWriter.flush();
        csvWriter.close();
    }

    public void writeOwners(FileWriter csvWriter, ArrayList<Owner> owners) throws IOException {
        csvWriter.append("id");
        csvWriter.append(",");
        csvWriter.append("lastname");
        csvWriter.append(",");
        csvWriter.append("firstname");
        csvWriter.append(",");
        csvWriter.append("email");
        csvWriter.append(",");
        csvWriter.append("password");
        csvWriter.append(",");
        csvWriter.append("phonenumber");
        csvWriter.append("\n");

        for (int i = 0; i < owners.size(); i++){
            Owner currentOwner = owners.get(i);
            csvWriter.append(String.format("%d",currentOwner.getId()));
            csvWriter.append(",");
            csvWriter.append(currentOwner.getLastName());
            csvWriter.append(",");
            csvWriter.append(currentOwner.getFirstName());
            csvWriter.append(",");
            csvWriter.append(currentOwner.getEmail());
            csvWriter.append(",");
            csvWriter.append(currentOwner.getPassword());
            csvWriter.append(",");
            csvWriter.append(currentOwner.getPhoneNumber());
            csvWriter.append("\n");

        }

        csvWriter.flush();
        csvWriter.close();
    }

    public void writeAnimals(FileWriter csvWriter, ArrayList<Animal> animals) throws IOException {
        csvWriter.append("id");
        csvWriter.append(",");
        csvWriter.append("name");
        csvWriter.append(",");
        csvWriter.append("species");
        csvWriter.append(",");
        csvWriter.append("yearofbirth");
        csvWriter.append(",");
        csvWriter.append("neutered");
        csvWriter.append(",");
        csvWriter.append("gender");
        csvWriter.append(",");
        csvWriter.append("weight_kg");
        csvWriter.append(",");
        csvWriter.append("owner_id");
        csvWriter.append("\n");

        for (int i = 0; i < animals.size(); i++){
            Animal currentAnimal = animals.get(i);
            csvWriter.append(String.format("%d",currentAnimal.getId()));
            csvWriter.append(",");
            csvWriter.append(currentAnimal.getName());
            csvWriter.append(",");
            csvWriter.append(currentAnimal.getSpecies());
            csvWriter.append(",");
            csvWriter.append(String.format("%d",currentAnimal.getYearOfBirth()));
            csvWriter.append(",");
            csvWriter.append(String.valueOf(currentAnimal.isNeutered()));
            csvWriter.append(",");
            csvWriter.append(currentAnimal.getGender());
            csvWriter.append(",");
            csvWriter.append(String.format("%d",currentAnimal.getWeight_kg()));
            csvWriter.append(",");
            csvWriter.append(String.format("%d",currentAnimal.getOwner_id()));
            csvWriter.append("\n");

        }

        csvWriter.flush();
        csvWriter.close();
    }

    public void writeVisits(FileWriter csvWriter, ArrayList<DoctorVisit> visits) throws IOException {
        csvWriter.append("id");
        csvWriter.append(",");
        csvWriter.append("visitdate");
        csvWriter.append(",");
        csvWriter.append("doctor_id");
        csvWriter.append(",");
        csvWriter.append("animal_id");
        csvWriter.append(",");
        csvWriter.append("reasonforvisit");
        csvWriter.append(",");
        csvWriter.append("diagnosis");
        csvWriter.append("\n");

        for (int i = 0; i < visits.size(); i++){
            DoctorVisit currentVisit = visits.get(i);
            csvWriter.append(String.format("%d",currentVisit.getId()));
            csvWriter.append(",");
            csvWriter.append(String.valueOf(currentVisit.getVisitDate()));
            csvWriter.append(",");
            csvWriter.append(String.format("%d",currentVisit.getDoctor_id()));
            csvWriter.append(",");
            csvWriter.append(String.format("%d",currentVisit.getAnimal_id()));
            csvWriter.append(",");
            csvWriter.append(currentVisit.getReasonForVisit());
            csvWriter.append(",");
            csvWriter.append(currentVisit.getDiagnosis());
            csvWriter.append("\n");

        }

        csvWriter.flush();
        csvWriter.close();
    }
}
