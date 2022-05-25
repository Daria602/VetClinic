package repos;

import database.Database;
import entities.Doctor;
import entities.DoctorVisit;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VisitRepo {
    private final Database database;

    public VisitRepo(){
        this.database = new Database();
    }

    public boolean insertNewDoctorVisit(DoctorVisit doctorVisit){

        String query = "insert into doctorvisit (visitdate, doctor_id, animal_id, reasonforvisit, diagnosis) values (?, ?, ?, ?, ?)";

        try{
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
            preparedStatement.setDate(1, doctorVisit.getVisitDate());
            preparedStatement.setInt(2, doctorVisit.getDoctor_id());
            preparedStatement.setInt(3, doctorVisit.getAnimal_id());
            preparedStatement.setString(4, doctorVisit.getReasonForVisit());
            preparedStatement.setString(5, doctorVisit.getDiagnosis());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) return true;

        }catch(SQLException e){
            e.printStackTrace();
        }


        return false;
    }
}
