package services;

import entities.Doctor;
import managers.VisitManager;
import repos.DoctorRepo;
import java.util.ArrayList;

public class DoctorService {
    private DoctorRepo dr;

    public DoctorService(){
        this.dr = new DoctorRepo();
    }

    public Doctor loginDoctor(String email, String password){
        Doctor loggedInDoctor = this.dr.loginDoctor(email, password);
        return loggedInDoctor;
    }

    public ArrayList<VisitManager> showVisits(boolean past, boolean today, int id){
        return  this.dr.showVisits(past, today, id);

    }

    public boolean registerNewDoctor(Doctor d){
        return this.dr.insertNewDoctor(d);
    }
}
