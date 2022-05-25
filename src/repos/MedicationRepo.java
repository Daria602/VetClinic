package repos;

import database.Database;
import entities.Medication;
import managers.MedicationManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class MedicationRepo {
    private Database database;

    public MedicationRepo(){
        this.database = new Database();
    }

    public boolean insertNewMedication(Medication m){
        String query = "insert into medication (medicationname, prescribedfor) values (?, ?)";
        try{
            PreparedStatement preparedStatement = this.database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, m.getMedicationName());
            preparedStatement.setString(2, m.getPrescribedFor());

            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0) return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateMedicationById(int medicationId, String field, String value){
        String query = String.format("update medication set %s = ? where medication_id = ?", field);
        try {
            PreparedStatement preparedStatement = this.database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, value);
            preparedStatement.setInt(2, medicationId);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) return true;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteMedicationById(int id){
        String query = "delete from medication where id = ?";
        try{
            PreparedStatement preparedStatement = this.database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return  false;
    }

    public ArrayList<Medication> getAllMedicationsByAnimalId(int id){
        HashMap<Integer, ArrayList<Medication>> result = new HashMap<>();
        String query = "select m.medication_id, animal_id, medicationname, prescribedfor from  medication m join visitmedication vm on (vm.medication_id = m.medication_id) join doctorvisit dv on (vm.visit_id = dv.visit_id)";
        try{
            PreparedStatement preparedStatement = this.database.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                if (result.get(resultSet.getInt("animal_id")) != null){
                    ArrayList<Medication> temp;
                    temp = result.get(resultSet.getInt("animal_id"));
                    Medication medication = new Medication(resultSet.getString("medicationname"), resultSet.getString("prescribedfor"));
                    medication.setId(resultSet.getInt("medication_id"));
                    temp.add(medication);
                    result.put(resultSet.getInt("animal_id"), temp);
                }
                else{
                    ArrayList<Medication> temp1 = new ArrayList<>();
                    temp1.add(new Medication(resultSet.getString("medicationname"), resultSet.getString("prescribedfor")));
                    result.put(resultSet.getInt("animal_id"), temp1);
                }
            }

        }catch(SQLException e){
            e.printStackTrace();
        }

        return result.get(id);
    }

    public ArrayList<Medication> getAllMedication(){
        ArrayList<Medication> result = new ArrayList<>();
        String query = "select * from medication";
        try{
            PreparedStatement preparedStatement = this.database.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Medication medication = new Medication(resultSet.getString("medicationname"), resultSet.getString("prescribedfor"));
                medication.setId(resultSet.getInt("medication_id"));
                result.add(medication);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }



}
