package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class Database {
    private static final String CON_STR = "jdbc:postgresql://localhost:5433/VetClinic";
    private static final String USER = "postgres";
    private static final String PASSWORD = "Admin01pa55";

    private static Connection connection;

    public Database(){
        try{
            this.connection = DriverManager.getConnection(CON_STR, USER, PASSWORD);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
