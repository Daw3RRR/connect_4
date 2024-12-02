package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Az adatbázis kezeléséért felelős osztály, amely az eredmények hozzáadását, lekérdezését és az adatbázis-kapcsolat kezelését végzi.
 */
public class DatabaseManager {
    private Connection connection; // Az adatbázis-kapcsolat tárolására szolgáló mező

    /**
     * Konstruktor: létrehozza az adatbázis-kapcsolatot és inicializálja az adatbázist.
     */
    public DatabaseManager() {
        try {
            // SQLite adatbázis kapcsolat inicializálása
            connection = DriverManager.getConnection("jdbc:sqlite:game_scores.db");
            if (connection == null) {
                // Ha a kapcsolat nem jött létre, hibaüzenet jelenik meg
                System.err.println("Failed to create connection object.");
            } else {
                // Ha a kapcsolat sikeres, inicializáljuk az adatbázist
                System.out.println("Connected to database successfully.");
                initializeDatabase(); // Az adatbázis inicializálása (táblák létrehozása)
            }
        } catch (SQLException e) {
            // Hibakezelés, ha az adatbázis-kapcsolat nem jön létre
            System.err.println("Failed to connect to database: " + e.getMessage());
            connection = null; // Sikertelen kapcsolat esetén null érték adása
        }
    }

    /**
     * Az adatbázis inicializálása: létrehozza a szükséges táblákat és oszlopokat, ha még nem léteznek.
     */
    private void initializeDatabase() {
        try (Statement statement = connection.createStatement()) {
            // A `high_scores` nevű tábla létrehozása, ha nem létezik
            String createTableSql = "CREATE TABLE IF NOT EXISTS high_scores (" +
                    "player_name TEXT PRIMARY KEY, " + // Elsődleges kulcs: játékos neve
                    "score REAL DEFAULT 0, " +         // Pontszám alapértelmezés: 0
                    "wins INTEGER DEFAULT 0)";         // Győzelmek alapértelmezés: 0
            statement.executeUpdate(createTableSql); // SQL utasítás végrehajtása

            // Ellenőrzi, hogy létezik-e a `wins` oszlop a táblában
            ResultSet rs = connection.getMetaData().getColumns(
                    null, null, "high_scores", "wins");
            if (!rs.next()) { // Ha a "wins" oszlop nem létezik
                // Új oszlop hozzáadása az adatbázis táblához
                String alterTableSql = "ALTER TABLE high_scores ADD COLUMN wins INTEGER DEFAULT 0";
                statement.executeUpdate(alterTableSql); // SQL utasítás végrehajtása
            }
        } catch (SQLException e) {
            // Hiba kezelése az adatbázis inicializálása során
            System.err.println("Failed to initialize database: " + e.getMessage());
        }
    }

    /**
     * Eredmény hozzáadása az adott játékoshoz (pontszám növelése).
     *
     * @param playerName A játékos neve.
     * @param points     A hozzáadott pontok.
     */
    public void addResult(String playerName, double points) {
        try {
            if (isPlayerInDatabase(playerName)) {
                // Ha a játékos már létezik, frissítjük a pontszámát
                String updateSql = "UPDATE high_scores SET score = score + ? WHERE player_name = ?";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateSql)) {
                    updateStatement.setDouble(1, points); // Pontok hozzáadása
                    updateStatement.setString(2, playerName); // Játékos neve
                    updateStatement.executeUpdate(); // Frissítés végrehajtása
                }
            } else {
                // Ha a játékos még nem létezik, új bejegyzést készítünk
                String insertSql = "INSERT INTO high_scores (player_name, score) VALUES (?, ?)";
                try (PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {
                    insertStatement.setString(1, playerName); // Játékos neve
                    insertStatement.setDouble(2, points); // Pontszám
                    insertStatement.executeUpdate(); // Beillesztés végrehajtása
                }
            }
        } catch (SQLException e) {
            // Hiba kezelése az eredmény hozzáadása közben
            System.err.println("Failed to add result for player " + playerName + ": " + e.getMessage());
        }
    }

    /**
     * Ellenőrzi, hogy a játékos létezik-e az adatbázisban.
     *
     * @param playerName A játékos neve.
     * @return Igaz, ha a játékos létezik, hamis, ha nem.
     */
    private boolean isPlayerInDatabase(String playerName) {
        String checkSql = "SELECT 1 FROM high_scores WHERE player_name = ?";
        try (PreparedStatement checkStatement = connection.prepareStatement(checkSql)) {
            checkStatement.setString(1, playerName); // Feltétel: játékos neve
            ResultSet rs = checkStatement.executeQuery(); // Lekérdezés végrehajtása
            return rs.next(); // Igaz, ha a játékos létezik; hamis, ha nem
        } catch (SQLException e) {
            // Hiba kezelése az ellenőrzés során
            System.err.println("Failed to check player in database: " + e.getMessage());
            return false; // Hibás ellenőrzés esetén false-t ad vissza
        }
    }

    /**
     * Az összes játékos eredményének lekérdezése (név, pontszám, győzelmek).
     *
     * @return Az összes játékos eredményei.
     */
    public List<PlayerResult> getAllPlayersResults() {
        // Lista az eredmények tárolására
        List<PlayerResult> playerResults = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            // Játékosok adatai lekérdezése és rendezése
            String sql = "SELECT player_name, score, wins FROM high_scores ORDER BY wins DESC, score DESC";
            ResultSet rs = statement.executeQuery(sql); // Lekérdezés végrehajtása

            // Az eredményhalmaz feldolgozása
            while (rs.next()) {
                // Játékos neve
                String name = rs.getString("player_name");
                // Pontszám
                double score = rs.getDouble("score");
                // Győzelmek száma
                int wins = rs.getInt("wins");
                // Új eredmény hozzáadása a listához
                playerResults.add(new PlayerResult(name, score, wins));
            }
        } catch (SQLException e) {
            // Hiba kezelése az eredmények lekérdezése közben
            System.err.println("Failed to fetch player results: " + e.getMessage());
        }
        return playerResults; // Visszaadja az eredmények listáját
    }

    /**
     * Az adatbázis-kapcsolat lezárása.
     */
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                // Kapcsolat lezárása
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            // Hiba kezelése a kapcsolat lezárása közben
            System.err.println("Failed to close database connection: " + e.getMessage());
        }
    }
}
