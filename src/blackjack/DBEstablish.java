package blackjack;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBEstablish {
    private static final String DB_URL = "jdbc:derby:BlackJackDB_Ebd;create=true";

    // Establishes a connection to the database
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
            createTable(conn); // Calls method to create table if it does not exist
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn; // Returns the database connection
    }

    // Creates the Players table in the database if it does not exist
    private static void createTable(Connection conn) {
        String createTableSQL = "CREATE TABLE Players (" +
                "id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
                "name VARCHAR(255) UNIQUE, " +
                "wins INT DEFAULT 0)";
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createTableSQL); // Executes SQL query to create table
        } catch (SQLException e) {
            if (!e.getSQLState().equals("X0Y32")) { // Checks if table already exists
                e.printStackTrace();
            }
        }
    }
}
