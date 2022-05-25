package services;

import entities.DoctorVisit;
import repos.VisitRepo;

public class VisitService {
    private VisitRepo vr;

    public VisitService(){
        this.vr = new VisitRepo();
    }

    public boolean scheduleNewDoctorVisit(DoctorVisit dv){
        return this.vr.insertNewDoctorVisit(dv);
    }
}
