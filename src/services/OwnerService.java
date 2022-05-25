package services;

import entities.Owner;
import repos.OwnerRepo;

import java.util.ArrayList;

public class OwnerService {
    private OwnerRepo ownerRepo;

    public OwnerService() {
        this.ownerRepo = new OwnerRepo();
    }

    public Owner loginOwner(String email, String password) {
        Owner owner = this.ownerRepo.loginOwner(email, password);
        return owner;
    }

    public boolean registerNewOwner(Owner ow) {
        return this.ownerRepo.insertNewOwner(ow);
    }

    public ArrayList<Owner> getAllOwners() {
        return this.ownerRepo.getAll();
    }

    public boolean updateOwner(int idOwner, String fieldToUpdate, String newValue) {
        return this.ownerRepo.updateOwner(idOwner, fieldToUpdate, newValue);
    }
}