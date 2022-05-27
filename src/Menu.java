import entities.*;
import managers.AnimalManager;
import managers.MedicationManager;
import managers.VisitManager;
import services.*;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private final DoctorService doctorService;
    private final AnimalService animalService;
    private final VisitService visitService;
    private final OwnerService ownerService;
    private final MedicationService medicationService;
    private Auditing auditing;
    private final Scanner scanner;
    public boolean running;
    public static final String titleMenu = """
                        Welcome to the VetClinic!
            """;
    public static final String startMenu = """
                        
                        Enter your choice:
            0 - Exit
            1 - Login
            2 - Register as an Owner
            """;

    public Menu() throws IOException {
        this.doctorService = new DoctorService();
        this.animalService = new AnimalService();
        this.visitService = new VisitService();
        this.ownerService = new OwnerService();
        this.medicationService = new MedicationService();
        this.auditing = new Auditing();
        this.scanner = new Scanner(System.in);
        this.running = true;
    }

    public void start() throws IOException {
        options();

    }

    public void options() throws IOException {
        String starterLine = titleMenu + startMenu;
        while (this.running) {
            System.out.println(starterLine);
            String action = this.scanner.nextLine();
            starterLine = startMenu;
            switch (action) {
                case "0":
                    this.running = false;
                    break;

                case "1":
                    login();
                    break;

                case "2":
                    System.out.println("Trying register");
                    break;

                default:
                    System.out.println("Action not recognised.\n\n");
            }


        }


    }

    public void login() throws IOException {
        String loginOption = """
                            Enter your choice:
                0 - Back
                1 - Login as a Doctor
                2 - Login as an Owner
                """;
        System.out.println(loginOption);
        String loginAction = this.scanner.nextLine();
        while (!loginAction.equals("0")) {
            switch (loginAction) {
                case "1":
                    Doctor dr = doctorService.loginDoctor(scanEmail(), scanPassword());
                    if (dr != null) {
                        this.auditing.writeAnAction("doctor login");
                        displayDoctorsActions(dr);
                        return;
                    }
                    break;

                case "2":
                    Owner owner = ownerService.loginOwner(scanEmail(), scanPassword());
                    if (owner != null) {
                        this.auditing.writeAnAction("owner login");
                        displayOwnersActions(owner);
                        return;
                    }
                    break;

                default:
                    System.out.println("Action not recognised.\n\n");
            }
            System.out.println(loginOption);
            loginAction = this.scanner.nextLine();
        }

        this.auditing.closeTheFile();
    }

    private void displayDoctorsActions(Doctor dr) throws IOException {
        String doctorsActions = """
                0 - Exit
                1 - Review future visits
                2 - Review visits for today
                3 - Review past visits
                4 - View animals by owner
                5 - View a specific animal
                6 - Schedule new visit
                7 - Register new owner
                8 - Register new doctor
                9 - Register new animal
                10 - Enter new medication
                11 - Delete a medication
                12 - See all medications for specific animal
                13 - See all medications
                14 - Update medication by id
                15 - See all owners
                """;
        System.out.println(doctorsActions);
        String action = this.scanner.nextLine();
        while (!action.equals("0")) {
            switch (action) {
                case "1":
                    ArrayList<VisitManager> result = this.doctorService.showVisits(false, false, dr.getId());
                    this.auditing.writeAnAction("doctor sees future visits");
                    if (result.size() == 0) System.out.println("No future visits found.\n");
                    else {
                        for (int i = 0; i < result.size(); i++) {
                            System.out.println(result.get(i));
                        }
                    }
                    break;
                case "2":
                    ArrayList<VisitManager> result1 = this.doctorService.showVisits(false, true, dr.getId());
                    this.auditing.writeAnAction("doctor sees today visits");
                    if (result1.size() == 0) System.out.println("No visits for today found.\n");
                    else {
                        for (int i = 0; i < result1.size(); i++) {
                            System.out.println(result1.get(i));
                        }
                    }
                    break;
                case "3":
                    ArrayList<VisitManager> result2 = this.doctorService.showVisits(true, false, dr.getId());
                    this.auditing.writeAnAction("doctor sees past visits");
                    if (result2.size() == 0) System.out.println("No past visits found.\n");
                    else {
                        for (int i = 0; i < result2.size(); i++) {
                            System.out.println(result2.get(i));
                        }
                    }
                    break;
                case "4":
                    System.out.println("Enter the id of the owner: ");
                    int id1 = Integer.parseInt(this.scanner.nextLine());
                    ArrayList<AnimalManager> result3 = this.animalService.getAnimalsByOwnerId(id1);
                    this.auditing.writeAnAction("doctor sees animals by ownerid");
                    if (result3.size() != 0) {
                        for (int i = 0; i < result3.size(); i++) {
                            System.out.println(result3.get(i));
                        }
                    } else System.out.println(String.format("Animals of owner of id %d were not found.", id1));
                    break;
                case "5":
                    System.out.println("Enter the id of the animal: ");
                    int id2 = Integer.parseInt(this.scanner.nextLine());
                    AnimalManager result4 = this.animalService.getAnimalById(id2);
                    if (result4 != null){
                        System.out.println(result4);
                        this.auditing.writeAnAction("doctor sees specific animal");
                    }
                    else System.out.println(String.format("Animal of id %d was not found.", id2));

                    break;
                case "6":
                    int doctor_id = dr.getId();
                    System.out.println("Enter id of animal:");
                    int animal_id = Integer.parseInt(this.scanner.nextLine());
                    System.out.println("Enter reason for the visit: ");
                    String reason = this.scanner.nextLine();
                    System.out.println("Enter the date for visit (format yyyy-mm-dd): ");
                    String dateString = this.scanner.nextLine();
                    try {
                        Date visitDate = Date.valueOf(dateString);
                        DoctorVisit dv = new DoctorVisit(visitDate, doctor_id, animal_id, reason, "null");
                        boolean doctorVisitInserted = this.visitService.scheduleNewDoctorVisit(dv);
                        if (doctorVisitInserted){
                            System.out.println("New visit scheduled with success!");
                            this.auditing.writeAnAction("doctor schedules new visit");
                        }
                        else System.out.println("Something went wrong...");

                    } catch (IllegalArgumentException e) {
                        System.out.println("Date was not in a correct format. Correct format is yyyy-mm-dd. For example: 2022-03-01");
                    }

                    break;
                case "7":
                    System.out.println("Enter new owner's last name");
                    String lname = this.scanner.nextLine();
                    System.out.println("Enter new owner's first name");
                    String fname = this.scanner.nextLine();
                    System.out.println("Enter new owner's phone number");
                    String phoneNumber = this.scanner.nextLine();
                    System.out.println("Enter new owner's email");
                    String email = this.scanner.nextLine();
                    System.out.println("Enter new owner's password");
                    String password = this.scanner.nextLine();
                    boolean ownerInserted = this.ownerService.registerNewOwner(new Owner(lname, fname, email, password, phoneNumber));
                    if (ownerInserted) {
                        System.out.println("New owner registered successfully!");
                        this.auditing.writeAnAction("doctor added new owner");
                    }
                    else System.out.println("Something went wrong...");

                    break;
                case "8":
                    System.out.println("Enter new doctor's last name");
                    String lnamed = this.scanner.nextLine();
                    System.out.println("Enter new doctor's first name");
                    String fnamed = this.scanner.nextLine();
                    System.out.println("Enter new doctor's phone number");
                    String phoneNumberd = this.scanner.nextLine();
                    System.out.println("Enter new doctor's email");
                    String emaild = this.scanner.nextLine();
                    System.out.println("Enter new doctor's password");
                    String passwordd = this.scanner.nextLine();
                    System.out.println("Enter new doctor's specialization");
                    String specialization = this.scanner.nextLine();
                    System.out.println("Enter new doctor's year of graduation");
                    int yearOfGrad = Integer.parseInt(this.scanner.nextLine());
                    boolean inserted = this.doctorService.registerNewDoctor(new Doctor(lnamed, fnamed, phoneNumberd, emaild, passwordd, specialization, yearOfGrad));
                    if (inserted){
                        System.out.println("New doctor added successfully");
                        this.auditing.writeAnAction("doctor added new doctor");
                    }
                    else System.out.println("Something went wrong...");
                    break;

                case "9":
                    //register new animal
                    System.out.println("Enter animal name: ");
                    String name = this.scanner.nextLine();
                    System.out.println("Enter animal species: ");
                    String species = this.scanner.nextLine();
                    System.out.println("Enter animal year of birth: ");
                    int yearOfBirth = Integer.parseInt(this.scanner.nextLine());
                    System.out.println("Was animal neutered? (true or false): ");
                    boolean wasNeutered = Boolean.parseBoolean(this.scanner.nextLine());
                    System.out.println("Enter animal gender: ");
                    String gender = this.scanner.nextLine();
                    System.out.println("Enter animal weight(kg): ");
                    int weight = Integer.parseInt(this.scanner.nextLine());
                    System.out.println("Enter the id of owner: ");
                    int ownerId = Integer.parseInt(this.scanner.nextLine());

                    boolean animalInserted = this.animalService.registerNewAnimal(new Animal(name, species, yearOfBirth, wasNeutered, gender, weight, ownerId));
                    if (animalInserted){
                        System.out.println("New animal added successfully");
                        this.auditing.writeAnAction("doctor added new animal");
                    }
                    else System.out.println("Something went wrong...");
                    break;

                case "10":
                    System.out.println("Enter new medication name:");
                    String medName = this.scanner.nextLine();
                    System.out.println("What is it usually prescribed for?");
                    String prescribedFor = this.scanner.nextLine();
                    boolean medicationInserted = this.medicationService.addNewMedication(new Medication(medName, prescribedFor));
                    if (medicationInserted){
                        System.out.println("Added successfully!");
                        this.auditing.writeAnAction("doctor added new medication");
                    }
                    else System.out.println("Something went wrong...");
                    break;

                case "11":
                    System.out.println("Enter the id of the medication to delete:");
                    int id = Integer.parseInt(this.scanner.nextLine());
                    boolean deleted = this.medicationService.removeMedication(id);
                    if (deleted){
                        System.out.println("Removed successfully!");
                        this.auditing.writeAnAction("doctor removed medication");
                    }
                    else System.out.println("Something went wrong...");
                case "12":
                    System.out.println("Enter animal id:");
                    int animalId = Integer.parseInt(this.scanner.nextLine());
                    ArrayList<Medication> resultMedication = this.medicationService.getMedicationForSpecificAnimal(animalId);
                    this.auditing.writeAnAction("doctor searched medication by animal_id");
                    if (resultMedication == null) System.out.println("No medication was found.");
                    else {
                        for (int i = 0; i < resultMedication.size(); i++) {
                            System.out.println(resultMedication.get(i));
                        }
                    }

                    break;
                case "13":
                    ArrayList<Medication> meds = this.medicationService.getAllMedication();
                    this.auditing.writeAnAction("doctor searched all medication");
                    if (meds != null){
                        for (int i = 0; i < meds.size(); i++){
                            System.out.println(meds.get(i));
                        }
                    }
                    break;

                case "14":
                    System.out.println("Enter the id of medication to remove:");
                    int idMed = Integer.parseInt(this.scanner.nextLine());
                    System.out.println("Enter the field to update (medicationname, prescribedfor): ");
                    String fieldToUpdate = this.scanner.nextLine();
                    System.out.println("Enter the new value for the field: ");
                    String newValue = this.scanner.nextLine();
                    if (!fieldToUpdate.equals("medicationname") && !fieldToUpdate.equals("prescribedfor")){
                        System.out.println("Incorrect field.");
                    } else{
                        boolean updatedMedication = this.medicationService.updateMedicationById(idMed, fieldToUpdate, newValue);
                        if (updatedMedication){
                            System.out.println("Updated successfully!");
                            this.auditing.writeAnAction("doctor updates medication");
                        }
                        else System.out.println("Something went wrong...");
                    }

                    break;

                case "15":
                    ArrayList<Owner> allOwners = this.ownerService.getAllOwners();
                    this.auditing.writeAnAction("doctor sees all owners");
                    if (allOwners != null){
                        for (int i = 0; i < allOwners.size(); i++){
                            System.out.println(allOwners.get(i));
                        }
                    }

                    break;
                default:
                    System.out.println("Action not recognised.\n\n");

            }
            System.out.println(doctorsActions);
            action = this.scanner.nextLine();

        }

    }

    private void displayOwnersActions(Owner owner) throws IOException {
        owner.setPets(this.animalService.getAnimalsByOwnerId(owner.getId()));
        String ownersActions = """
                0 - Exit
                1 - View my animals
                2 - Register new animal
                3 - Schedule new visit for a doctor
                4 - Update my pet
                5 - View my pets medication
                6 - Update my profile
                """;

        System.out.println(ownersActions);
        String action = this.scanner.nextLine();
        while (!action.equals("0")) {
            switch (action) {
                case "1":
                    ArrayList<AnimalManager> myAnimals = this.animalService.getAnimalsByOwnerId(owner.getId());
                    this.auditing.writeAnAction("owner sees his animals");
                    if (myAnimals != null) {
                        System.out.println("My animals <3 :");
                        for (int i = 0; i < myAnimals.size(); i++) {
                            System.out.println(myAnimals.get(i));
                        }
                    }
                    break;
                case "2":
                    //register new animal
                    System.out.println("Enter animal name: ");
                    String name = this.scanner.nextLine();
                    System.out.println("Enter animal species: ");
                    String species = this.scanner.nextLine();
                    System.out.println("Enter animal year of birth: ");
                    int yearOfBirth = Integer.parseInt(this.scanner.nextLine());
                    System.out.println("Was animal neutered? (true or false): ");
                    boolean wasNeutered = Boolean.parseBoolean(this.scanner.nextLine());
                    System.out.println("Enter animal gender: ");
                    String gender = this.scanner.nextLine();
                    System.out.println("Enter animal weight(kg): ");
                    int weight = Integer.parseInt(this.scanner.nextLine());
                    int ownerId = owner.getId();
                    boolean animalInserted = this.animalService.registerNewAnimal(new Animal(name, species, yearOfBirth, wasNeutered, gender, weight, ownerId));
                    if (animalInserted){
                        System.out.println("New animal added successfully");
                        this.auditing.writeAnAction("owner inserts new animal");
                    }
                    else System.out.println("Something went wrong...");
                    break;
                case "3":
                    System.out.println("Enter id of a doctor:");
                    int doctor_id = Integer.parseInt(this.scanner.nextLine());
                    System.out.println("Enter id of animal:");
                    int animal_id = Integer.parseInt(this.scanner.nextLine());
                    System.out.println("Enter reason for the visit: ");
                    String reason = this.scanner.nextLine();
                    System.out.println("Enter the date for visit (format yyyy-mm-dd): ");
                    String dateString = this.scanner.nextLine();
                    try {
                        Date visitDate = Date.valueOf(dateString);
                        DoctorVisit dv = new DoctorVisit(visitDate, doctor_id, animal_id, reason, "null");
                        boolean doctorVisitInserted = this.visitService.scheduleNewDoctorVisit(dv);
                        if (doctorVisitInserted){
                            System.out.println("New visit scheduled with success!");
                            this.auditing.writeAnAction("owner schedules new visit");
                        }
                        else System.out.println("Something went wrong...");

                    } catch (IllegalArgumentException e) {
                        System.out.println("Date was not in a correct format. Correct format is yyyy-mm-dd. For example: 2022-03-01");
                    }
                    break;
                case "4":
                    if (owner.getPets().size() == 0) System.out.println("You dont have any pets yet...");
                    else {
                        System.out.println("Enter the name of the animal to update");
                        for (int i = 0; i < owner.getPets().size(); i++) {
                            System.out.println(owner.getPets().get(i));
                        }
                        String animalToUpdateName = this.scanner.nextLine();
                        int idAnimal = this.animalService.getIdAnimalByNameAndOwnerId(animalToUpdateName, owner.getId());
                        if (idAnimal == -1) System.out.println("Animal was not found. Check the spelling(it's case sensitive)");
                        else{
                            System.out.println("What field would you like to update: name, species, gender or weight_kg?");
                            String fieldToUpdate = this.scanner.nextLine();
                            System.out.println("What is the new value?");
                            String value = this.scanner.nextLine();
                            boolean animalUpdated = this.animalService.updateField(idAnimal, fieldToUpdate, value);
                            if (animalUpdated){
                                System.out.println("Updated successfully!");
                                this.auditing.writeAnAction("owner updates animal");
                            }
                            else System.out.println("Something went wrong...");
                        }
                    }
                    break;

                case "5":
                    System.out.println("Enter the name of your pet (case sensitive):");
                    String petName = this.scanner.nextLine();
                    int animalId = this.animalService.getIdAnimalByNameAndOwnerId(petName, owner.getId());
                    ArrayList<Medication> resultMedication = this.medicationService.getMedicationForSpecificAnimal(animalId);
                    ArrayList<MedicationManager> finalResult = this.medicationService.convertListOfMedication(resultMedication);
                    this.auditing.writeAnAction("owner sees animal's medication");

                    if (finalResult.size() == 0) System.out.println("No medication was found.");
                    else {
                        for (int i = 0; i < finalResult.size(); i++) {
                            System.out.println(finalResult.get(i));
                        }
                    }
                    break;

                case "6":
                    System.out.println("What field would you like to update? Select from (lastname, firstname, phonenumber, email, password)");
                    String fieldToUpdate = this.scanner.nextLine();
                    if (fieldToUpdate.equals("lastname") || fieldToUpdate.equals("firstname") || fieldToUpdate.equals("phonenumber") || fieldToUpdate.equals("email") || fieldToUpdate.equals("password")){
                        System.out.println("What is the new value?");
                        String newValue = this.scanner.nextLine();
                        boolean updatedOwner = this.ownerService.updateOwner(owner.getId(), fieldToUpdate, newValue);
                        if (updatedOwner){
                            System.out.println("Updated successfully!");
                            this.auditing.writeAnAction("owner updates their profile");
                        }
                        else System.out.println("Something went wrong...");
                    }else System.out.println("Wrong field. Select from (lastname, firstname, phonenumber, email, password)");


                default:
                    System.out.println("Action not recognised.\n\n");

            }
            System.out.println(ownersActions);
            action = this.scanner.nextLine();

        }

    }

    private String scanEmail() {

        System.out.println("Your email: ");

        return this.scanner.nextLine();
    }

    private String scanPassword() {

        System.out.println("Your password: ");

        return this.scanner.nextLine();
    }
}
