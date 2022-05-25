import entities.Animal;
import entities.Doctor;
import entities.DoctorVisit;
import entities.Owner;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;


public class CSVActions {
    ReadFromCSV readFromCSV;
    WriteToCSV writeToCSV;

    public CSVActions(){
        this.readFromCSV = new ReadFromCSV();
        this.writeToCSV = new WriteToCSV();
    }


//= new BufferedReader(new FileReader(pathToCsv));
    public void start() throws IOException {

        BufferedReader csvDoctorsReader = new BufferedReader(new FileReader("C:/Users/izoto/IdeaProjects/VetClinic/src/doctors.csv"));
        BufferedReader csvOwnersReader = new BufferedReader(new FileReader("C:/Users/izoto/IdeaProjects/VetClinic/src/owners.csv"));
        BufferedReader csvAnimalsReader = new BufferedReader(new FileReader("C:/Users/izoto/IdeaProjects/VetClinic/src/animals.csv"));
        BufferedReader csvVisitsReader = new BufferedReader(new FileReader("C:/Users/izoto/IdeaProjects/VetClinic/src/doctorvisit.csv"));

        ArrayList<Doctor> doctors =  this.readFromCSV.readDoctors(csvDoctorsReader);
        ArrayList<Owner> owners = this.readFromCSV.readOwners(csvOwnersReader);
        ArrayList<Animal> animals = this.readFromCSV.readAnimals(csvAnimalsReader);
        ArrayList<DoctorVisit> visits = this.readFromCSV.readVisits(csvVisitsReader);


        Doctor newDoctor = new Doctor("Turner", "James", "123456", "doctor3@info.ro", "doctor3","general",2000);
        newDoctor.setId(3);
        doctors.add(newDoctor);

        Owner newOwner = new Owner("Gordy", "Gord", "owner3@info.ro", "owner3", "123456");
        newOwner.setId(3);
        owners.add(newOwner);

        Animal newAnimal = new Animal("Doggio", "cat", 2020, false, "female", 3, 3);
        newAnimal.setId(3);
        animals.add(newAnimal);

        DoctorVisit newVisit = new DoctorVisit(Date.valueOf("2022-05-14"), 3, 3, "weakness", "none");
        newVisit.setId(3);
        visits.add(newVisit);


        FileWriter csvDoctorsWriter = new FileWriter("doctors.csv");
        FileWriter csvOwnersWriter = new FileWriter("owners.csv");
        FileWriter csvAnimalsWriter = new FileWriter("animals.csv");
        FileWriter csvVisitsWriter = new FileWriter("doctorvisit.csv");

        this.writeToCSV.writeDoctors(csvDoctorsWriter, doctors);
        this.writeToCSV.writeOwners(csvOwnersWriter, owners);
        this.writeToCSV.writeAnimals(csvAnimalsWriter, animals);
        this.writeToCSV.writeVisits(csvVisitsWriter, visits);


    }

    public static void main(String[] args) throws IOException {
        CSVActions csv = new CSVActions();
        csv.start();
    }

}
