package az.edu.turing.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    private static final String URL = System.getenv("POSTGRES_URL");
    public static final String USER = System.getenv("POSTGRES_USER");
    public static final String PASSWORD = System.getenv("POSTGRES_PASSWORD");

    public Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
