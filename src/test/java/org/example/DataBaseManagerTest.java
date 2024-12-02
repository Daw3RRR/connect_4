package org.example;

import org.example.DatabaseManager;
import org.example.PlayerResult;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DataBaseManagerTest {
    private DatabaseManager databaseManager;

    @BeforeEach
    public void setUp() {
        databaseManager = new DatabaseManager();
    }

    @AfterEach
    public void tearDown() {
        databaseManager.close();
    }

    @Test
    public void testInitializeDatabase() {
        // Az inicializálás után ellenőrizzük, hogy létezik-e a tábla és az oszlopok
        // ...
    }
    // ... itt következnek a teszt metódusok ...
    @Test
    public void testAddResult() {
        databaseManager.addResult("player1", 100);
        // Ellenőrizzük, hogy a játékos bekerült-e az adatbázisba és a pontszám helyes-e
        // ...
    }
    @Test
    public void testGetAllPlayersResults() {
        // Adjunk hozzá néhány játékost az adatbázishoz
        // ...
        List<PlayerResult> results = databaseManager.getAllPlayersResults();
        // Ellenőrizzük, hogy a lista tartalmazza a hozzáadott játékosokat és a sorrend megfelelő
        // ...
    }
}