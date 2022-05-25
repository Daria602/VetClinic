package services;

import entities.Animal;
import managers.AnimalManager;
import repos.AnimalRepo;

import java.util.ArrayList;
import java.util.HashMap;

public class AnimalService {
    private AnimalRepo ar;

    public AnimalService(){
        this.ar = new AnimalRepo();
    }

    public AnimalManager getAnimalById(int id){
        return ar.getAnimalById(id);
    }
    public ArrayList<AnimalManager> getAnimalsByOwnerId(int id) { return ar.getAnimalsByOwnerId(id); }
    public boolean registerNewAnimal(Animal animal){ return ar.insertNewAnimal(animal); }
    public int getIdAnimalByNameAndOwnerId(String name, int ownerId) { return ar.getIdAnimalByNameAndOwnerId(name, ownerId); }
    public boolean updateField(int idAnimal, String fieldString, String value) { return  ar.updateAnimal(idAnimal, fieldString, value); }



}
