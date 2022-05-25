package repos;

import database.Database;
import entities.Animal;
import managers.AnimalManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AnimalRepo {
    private Database database;

    public AnimalRepo() {
        this.database = new Database();
    }

    public AnimalManager getAnimalById(int id) {
        String query = "select * from animals join owners on (animals.owner_id = owners.owner_id) where animal_id = ?";
        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next())
                return new AnimalManager(resultSet.getString("name"), resultSet.getString("species"), resultSet.getInt("yearOfBirth"), resultSet.getBoolean("neutered"), resultSet.getString("gender"), resultSet.getInt("weight_kg"), resultSet.getString("lastname") + " " + resultSet.getString("firstname"));
            else return null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<AnimalManager> getAnimalsByOwnerId(int id) {
        ArrayList<AnimalManager> result = new ArrayList<>();
        String query = "select * from animals join owners on (animals.owner_id = owners.owner_id) where animals.owner_id = ?";
        try {
            PreparedStatement preparedStatement = this.database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                result.add(new AnimalManager(resultSet.getString("name"), resultSet.getString("species"), resultSet.getInt("yearOfBirth"), resultSet.getBoolean("neutered"), resultSet.getString("gender"), resultSet.getInt("weight_kg"), resultSet.getString("lastname") + " " + resultSet.getString("firstname")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;

    }

    public int getIdAnimalByNameAndOwnerId(String animalName, int ownerId){
        String query = "select * from animals where owner_id = ? and name = ?";
        try {
            PreparedStatement preparedStatement = this.database.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, ownerId);
            preparedStatement.setString(2, animalName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) return resultSet.getInt("animal_id");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean updateAnimal(int idAnimal, String fieldString, String value) {
        String query = String.format("update animals set %s = ? where animal_id = ?", fieldString);
        if (fieldString.equals("weight_kg")){
            int newValue = Integer.parseInt(value);
            try{
                PreparedStatement preparedStatement = this.database.getConnection().prepareStatement(query);
                preparedStatement.setInt(1, newValue);
                preparedStatement.setInt(2, idAnimal);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) return true;
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        else{

            try{
                PreparedStatement preparedStatement = this.database.getConnection().prepareStatement(query);
                preparedStatement.setString(1, value);
                preparedStatement.setInt(2, idAnimal);
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) return true;

            } catch (SQLException e){
                e.printStackTrace();
            }

        }

        return false;

    }

    public boolean insertNewAnimal(Animal animal) {

        String query = "insert into animals (name, species, yearofbirth, neutered, gender, weight_kg, owner_id) values (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = database.getConnection().prepareStatement(query);
            preparedStatement.setString(1, animal.getName());
            preparedStatement.setString(2, animal.getSpecies());
            preparedStatement.setInt(3, animal.getYearOfBirth());
            preparedStatement.setBoolean(4, animal.isNeutered());
            preparedStatement.setString(5, animal.getGender());
            preparedStatement.setInt(6, animal.getWeight_kg());
            preparedStatement.setInt(7, animal.getOwner_id());


            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}
