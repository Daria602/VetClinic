package managers;

public class AnimalManager {
    private final String animalName;
    private final String species;
    private final int yearOfBirth;
    private final boolean neutered;
    private final String gender;
    private final int weight;
    private final String ownerName;
    private static final String SEP = " | ";

    public AnimalManager(String animalName, String species, int yearOfBirth, boolean neutered, String gender, int weight, String ownerName) {
        this.animalName = animalName;
        this.species = species;
        this.yearOfBirth = yearOfBirth;
        this.neutered = neutered;
        this.gender = gender;
        this.weight = weight;
        this.ownerName = ownerName;
    }

    @Override
    public String toString(){
        return "Name: " + this.animalName
                + SEP + "Species: " + this.species
                + SEP + "Born: " + this.yearOfBirth
                + SEP + "Neutered: " + this.neutered
                + SEP + "Gender: " + this.gender
                + SEP + "Weight(kg): " + this.weight
                + SEP + "Owner: " + this.ownerName;
    }


}
