package entities;

import interfaces.IIdentifier;

public class Animal implements IIdentifier {
    private int animalId;
    private String name;
    private String species;
    private int yearOfBirth;
    private boolean neutered;
    private String gender; // male, female or other
    private int weight_kg;
    private int owner_id; //owner_id



    public Animal(String name, String species, int yearOfBirth, boolean neutered, String gender, int weight_kg, int owner_id) {
        this.name = name;
        this.species = species;
        this.yearOfBirth = yearOfBirth;
        this.neutered = neutered;
        this.gender = gender;
        this.weight_kg = weight_kg;
        this.owner_id = owner_id;
    }
    public String getSpecies() {
        return species;
    }

    public String getName() {
        return name;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public boolean isNeutered() {
        return neutered;
    }

    public String getGender() {
        return gender;
    }

    public int getWeight_kg() {
        return weight_kg;
    }

    public int getOwner_id() {
        return owner_id;
    }

    @Override
    public int getId() {
        return this.animalId;
    }

    @Override
    public void setId(int id) {
        this.animalId = id;
    }
}
