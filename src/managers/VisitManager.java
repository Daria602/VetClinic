package managers;

import java.sql.Date;

public class VisitManager {
    private final String animalName;
    private final String ownerName;
    private final Date date;


    public VisitManager(String animalName, String ownerName, Date date) {
        this.animalName = animalName;
        this.ownerName = ownerName;
        this.date = date;

    }

    @Override
    public String toString(){
        return "Date: " + this.date.toString() + " | Animal: " + this.animalName + " | Owner: " + this.ownerName;
    }

}
