/*** package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PlayerScoreManager {

    private static final String DATABASE_URL = "jdbc:sqlite:game_scores.db"; // Az adatbázis fájl elérési útja

    // Játékos győzelmeinek frissítése
    public static void updatePlayerScore(String playerName) {
        String sql = "INSERT INTO players (Player, games_won) " +
                "VALUES (?, 1) " +
                "ON CONFLICT(Player) DO UPDATE SET games_won = games_won + 1;";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:game_scores.db");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, playerName);
            pstmt.executeUpdate();
            System.out.println("Player's score updated in the database.");
        } catch (SQLException e) {
            System.out.println("Error updating player score: " + e.getMessage());
        }
    }


    // Top 5 játékos lekérdezése
    public static void getTop5Players() {
        String querySQL = "SELECT name, games_won FROM players ORDER BY games_won DESC LIMIT 5;";

        try (Connection conn = DriverManager.getConnection(DATABASE_URL);
             var stmt = conn.createStatement();
             var rs = stmt.executeQuery(querySQL)) {

            System.out.println("Top 5 Players:");
            while (rs.next()) {
                String name = rs.getString("name");
                int gamesWon = rs.getInt("games_won");
                System.out.println(name + " - " + gamesWon + " games won");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving top 5 players: " + e.getMessage());
        }
    }
}
***/