package repos;

import database.Database;
import entities.Doctor;
import managers.VisitManager;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class DoctorRepo {
    private Database database;

    public DoctorRepo() {
        this.database = new Database();
    }

    public Doctor loginDoctor(String email, String password) {
        String query = String.format("select * from doctors where email = '%s'", email);
        try {
            PreparedStatement preparedQuery = database.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedQuery.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getString("password").equals(password)) {
                    System.out.println("Logged in successfully!");
                    Doctor loggedInDoctor = new Doctor(
                            resultSet.getString("lastName"),
                            resultSet.getString("firstName"),
                            resultSet.getString("phoneNumber"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("specialization"),
                            resultSet.getInt("yearOfGraduation")
                    );
                    loggedInDoctor.setId(resultSet.getInt("doctor_id"));
                    loggedInDoctor.setLoggedIn(true);
                    return loggedInDoctor;
                } else {
                    System.out.println("Wrong password");
                    return null;
                }
            } else {
                System.out.println("No");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;

    }

    public ArrayList<VisitManager> showVisits(boolean past, boolean today, int id) {


        Date date = new Date(Calendar.getInstance().getTime().getTime());
        String query = "select * from doctorvisit where 1 = 0";
        PreparedStatement preparedQuery;
        if (today) {

            query = "select a.name, o.lastname, o.firstname, dv.visitdate from doctorvisit dv join animals a on(dv.animal_id = a.animal_id) join owners o on(a.owner_id = o.owner_id) where doctor_id = ? and dv.visitdate = ?";

        } else {
            if (past) {
                query = "select a.name, o.lastname, o.firstname, dv.visitdate from doctorvisit dv join animals a on(dv.animal_id = a.animal_id) join owners o on(a.owner_id = o.owner_id) where doctor_id = ? and dv.visitdate < ?";


            } else {

                query = "select a.name, o.lastname, o.firstname, dv.visitdate from doctorvisit dv join animals a on(dv.animal_id = a.animal_id) join owners o on(a.owner_id = o.owner_id) where doctor_id = ? and dv.visitdate > ?";

            }

        }
        ArrayList<VisitManager> listOfVisits = new ArrayList<>();
        try {

            preparedQuery = database.getConnection().prepareStatement(query);
            preparedQuery.setInt(1, id);
            preparedQuery.setDate(2, date);

            ResultSet resultSet = preparedQuery.executeQuery();

            while (resultSet.next()) {
                listOfVisits.add(new VisitManager(resultSet.getString("name"), resultSet.getString("lastName") + " " + resultSet.getString("firstName"), resultSet.getDate("visitdate")));
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listOfVisits;

    }

    public boolean insertNewDoctor(Doctor d){

        String query = "insert into doctors (lastname, firstname, phonenumber, email, password, specialization, yearofgraduation) values (?, ?, ?, ?, ?, ?, ?)";
        try{
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
            preparedStatement.setString(1,d.getLastName());
            preparedStatement.setString(2, d.getFirstName());
            preparedStatement.setString(3, d.getPhoneNumber());
            preparedStatement.setString(4, d.getEmail());
            preparedStatement.setString(5, d.getPassword());
            preparedStatement.setString(6, d.getSpecialization());
            preparedStatement.setInt(7, d.getYearOfGraduation());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) return true;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }


}