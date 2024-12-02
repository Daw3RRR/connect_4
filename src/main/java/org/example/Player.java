package org.example;

import java.util.Scanner;

/**
 * A játékos adatait kezelő osztály.
 * Felelős a játékos nevének lekéréséért és tárolásáért.
 */
public class Player {

    /**
     * A játékos neve.
     */
    public String name;

    /**
     * A Player osztály konstruktora.
     * Ha a név nincs beállítva, akkor bekéri a játékostól a nevét.
     */
    public Player() {
        // Ha a név üres vagy null, bekérjük a felhasználótól
        /*if (name == null || name.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Mi a neved?"); // Név bekérése
            name = scanner.nextLine();         // A név beolvasása
            System.out.println("Üdvözlünk, " + name + "!"); // Köszöntés
        }*/
    }
}
