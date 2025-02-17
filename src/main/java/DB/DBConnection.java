package DB;

import lombok.Getter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    @Getter
    private Connection connection;
    private DBConnection() throws SQLException {
        String URL = "jdbc:mysql://localhost:3306/thogakade";
        String userName = "root";
        String password = "root";
        connection = DriverManager.getConnection(URL,userName,password);
    }
    public static DBConnection getInstance() throws SQLException {
        return  instance==null? instance = new DBConnection():instance;
    }
}

//import lombok.Getter;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class DBConnection {
//    @Getter
//    private Connection connection;
//    private static  DBConnection instance;
//    private DBConnection() throws SQLException {
//
//        String URL = "jdbc:mysql://localhost:3306/ThogaKade";
//        String usserName = "root";
//        String password = "root";
//        connection= DriverManager.getConnection(URL,usserName,password);
//    }
//
//
//    public static DBConnection getInstance() throws SQLException {
//        return instance== null? instance = new DBConnection() : instance;
//    }
//}
