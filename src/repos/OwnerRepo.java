package repos;

import database.Database;
import entities.Owner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OwnerRepo {
    private Database database;
    public OwnerRepo(){
        this.database = new Database();
    }

    public Owner loginOwner(String email, String password){
        String query = String.format("select * from owners where email = '%s'", email);
        try {
            PreparedStatement preparedQuery = database.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedQuery.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getString("password").equals(password)) {
                    System.out.println("Logged in successfully!");
                    Owner loggedInOwner = new Owner(
                            resultSet.getString("lastname"),
                            resultSet.getString("firstname"),
                            resultSet.getString("email"),
                            resultSet.getString("password"),
                            resultSet.getString("phoneNumber")
                    );
                    loggedInOwner.setId(resultSet.getInt("owner_id"));
                    return loggedInOwner;
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

    public boolean updateOwner(int idOwner, String fieldToUpdate, String newValue){
        String query = String.format("update owners set %s = ? where owner_id = ?", fieldToUpdate);
        try{
            PreparedStatement preparedStatement = this.database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, newValue);
            preparedStatement.setInt(2, idOwner);
            int affectedRows = preparedStatement.executeUpdate();
            if (affectedRows > 0){
                return true;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<Owner> getAll(){
        String query = "select * from owners";
        ArrayList<Owner> result = new ArrayList<>();
        try{
            PreparedStatement preparedStatement = this.database.getConnection().prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Owner ow = new Owner(resultSet.getString("lastname"), resultSet.getString("firstname"), resultSet.getString("email"), resultSet.getString("password"), resultSet.getString("phonenumber"));
                ow.setId(resultSet.getInt("owner_id"));
                result.add(ow);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    public boolean insertNewOwner(Owner owner){
        String query = "insert into owners (lastname, firstname, phonenumber, email, password) values (?, ?, ?, ?, ?)";
        try{
            PreparedStatement preparedStatement = this.database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, owner.getLastName());
            preparedStatement.setString(2, owner.getFirstName());
            preparedStatement.setString(3, owner.getPhoneNumber());
            preparedStatement.setString(4, owner.getEmail());
            preparedStatement.setString(5, owner.getPassword());
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) return true;

        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
