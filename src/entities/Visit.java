package entities;

import interfaces.IIdentifier;
import java.sql.Date;

public class Visit implements IIdentifier {
    protected int visitId;
    protected Date visitDate;

    public Visit(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    @Override
    public int getId() {
        return this.visitId;
    }

    @Override
    public void setId(int id) {
        this.visitId = id;
    }
}
