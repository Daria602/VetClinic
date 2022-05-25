import entities.Animal;
import entities.Doctor;
import entities.DoctorVisit;
import entities.Owner;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

public class ReadFromCSV {

    public ArrayList<Doctor> readDoctors(BufferedReader csvReader) throws IOException {
        ArrayList<Doctor> newDoctor = new ArrayList<>();
        String row;
        row = csvReader.readLine();
        while ((row = csvReader.readLine()) != null){
            String[] data = row.split(",");
            int id = Integer.parseInt(data[0]);
            String lastname = data[1];
            String firstname = data[2];
            String phonenumber = data[3];
            String email = data[4];
            String password = data[5];
            String specialization = data[6];
            int yearOfGraduation = Integer.parseInt(data[7].strip());
            Doctor d = new Doctor(lastname, firstname, phonenumber, email, password,specialization, yearOfGraduation);
            d.setId(id);
            newDoctor.add(d);
        }
        return newDoctor;

    }

    public ArrayList<Owner> readOwners(BufferedReader csvReader) throws IOException {
        ArrayList<Owner> newOwner = new ArrayList<>();
        String row;
        row = csvReader.readLine();
        while ((row = csvReader.readLine()) != null){
            String[] data = row.split(",");
            int id = Integer.parseInt(data[0]);
            String lastname = data[1];
            String firstname = data[2];
            String email = data[3];
            String password = data[4];
            String phonenumber = data[5];

            Owner ow = new Owner(lastname, firstname, email, password, phonenumber);
            ow.setId(id);
            newOwner.add(ow);
        }
        return newOwner;
    }

    public ArrayList<Animal> readAnimals(BufferedReader csvReader) throws IOException {
        ArrayList<Animal> newAnimal = new ArrayList<>();
        String row;
        row = csvReader.readLine();
        while ((row = csvReader.readLine()) != null){
            String[] data = row.split(",");
            int id = Integer.parseInt(data[0]);
            String name = data[1];
            String species = data[2];
            int yearOfBirth = Integer.parseInt(data[3].strip());
            boolean neutered = Boolean.parseBoolean(data[4].strip());
            String gender = data[5];
            int weight_kg = Integer.parseInt(data[6].strip());
            int owner_id = Integer.parseInt(data[7].strip());


            Animal an = new Animal(name, species, yearOfBirth, neutered, gender, weight_kg, owner_id);
            an.setId(id);
            newAnimal.add(an);
        }
        return newAnimal;
    }

    public ArrayList<DoctorVisit> readVisits(BufferedReader csvReader) throws IOException {
        ArrayList<DoctorVisit> newVisits = new ArrayList<>();
        String row;
        row = csvReader.readLine();
        while ((row = csvReader.readLine()) != null){
            String[] data = row.split(",");
            int id = Integer.parseInt(data[0]);
            Date visitdate = Date.valueOf(data[1].strip());
            int doctor_id = Integer.parseInt(data[2].strip());
            int animal_id = Integer.parseInt(data[3].strip());
            String reason = data[4];
            String diagnosis = data[5];

            DoctorVisit dv = new DoctorVisit(visitdate, doctor_id, animal_id, reason, diagnosis);
            dv.setId(id);
            newVisits.add(dv);
        }
        return newVisits;
    }




}
