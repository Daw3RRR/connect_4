package org.example;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A játékosok legmagasabb pontszámainak kezelésére szolgáló osztály.
 * Felelős a játékosok eredményeinek megjelenítéséért, különösen a top 3 játékosért.
 */
public class HighScore {

    public DatabaseManager dbManager; // Az adatbázis-kezelő objektum, amely kezeli a játékosok eredményeit.

    /**
     * HighScore osztály konstruktora.
     * Létrehozza az adatbázis-kezelő objektumot.
     */
    public HighScore() {
        dbManager = new DatabaseManager();
    }

    /**
     * Megjeleníti a top 3 játékost a pontszámok alapján csökkenő sorrendben.
     * Az eredményeket az adatbázisból olvassa be, és a konzolra írja ki.
     */
    public void displayTop3() {
        List<PlayerResult> players = dbManager.getAllPlayersResults();

        // A játékosok rendezése pontszám alapján csökkenő sorrendben
        Collections.sort(players, new Comparator<PlayerResult>() {
            @Override
            public int compare(PlayerResult p1, PlayerResult p2) {
                return Double.compare(p2.getPoints(), p1.getPoints()); // Pontok alapján csökkenő sorrend
            }
        });

        // A legjobb 3 játékos kiírása
        System.out.println("Top 3 játékos (pontszám alapján):");
        for (int i = 0; i < Math.min(3, players.size()); i++) {
            PlayerResult player = players.get(i);
            System.out.println((i + 1) + ". " + player.getName() + " - " + player.getPoints() + " pont");
        }
    }
}
