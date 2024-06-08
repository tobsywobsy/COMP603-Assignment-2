package blackjack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBManager {

    private Connection conn;

    public DBManager() {
        conn = DBEstablish.connect(); // Establishes connection to the database
    }

    // Checks if a player already exists in the database
    private boolean playerExists(String name) {
        String query = "SELECT COUNT(*) FROM Players WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Adds a new player to the database if they don't already exist
    public void addPlayer(String name) {
        if (playerExists(name)) {
            System.out.println("Player already exists: " + name);
            return;
        }
        String insertPlayerSQL = "INSERT INTO Players (name) VALUES (?)";
        try (PreparedStatement pstmt = conn.prepareStatement(insertPlayerSQL)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate(); // Executes SQL query to insert player name into the database
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Increments the wins count for a player in the database
    public void incrementWins(String name) {
        String updateWinsSQL = "UPDATE Players SET wins = wins + 1 WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(updateWinsSQL)) {
            pstmt.setString(1, name);
            pstmt.executeUpdate(); // Executes SQL query to update wins count for a player
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Retrieves the number of wins for a player from the database
    public int getWins(String name) {
        String queryWinsSQL = "SELECT wins FROM Players WHERE name = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(queryWinsSQL)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery(); // Executes SQL query to retrieve wins count for a player
            if (rs.next()) {
                return rs.getInt("wins"); // Returns the wins count
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    // Retrieves the leaderboard data from the database
    public static String retrieveLeaderboardData() {
        StringBuilder leaderboardData = new StringBuilder();
        String query = "SELECT name, wins FROM Players"; // SQL query to select player names and wins
        try (Connection conn = DBEstablish.connect(); PreparedStatement pstmt = conn.prepareStatement(query); ResultSet rs = pstmt.executeQuery()) {
            leaderboardData.append(String.format("%-20s %s\n", "Name", "Wins")); // Appends column headers to the string builder
            leaderboardData.append("-----------------------------------\n");
            while (rs.next()) {
                String name = rs.getString("name"); // Retrieves player name from the result set
                int wins = rs.getInt("wins"); // Retrieves wins count from the result set
                leaderboardData.append(String.format("%-20s %d\n", name, wins)); // Appends player name and wins count to the string builder
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaderboardData.toString(); // Returns the leaderboard data as a string
    }
}
