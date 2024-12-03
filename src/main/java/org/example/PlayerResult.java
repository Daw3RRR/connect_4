package org.example;

/**
 * Az egyes játékosok eredményeit tároló osztály.
 * Tartalmazza a játékos nevét, pontjait és győzelmeinek számát.
 */
public class PlayerResult {

    private String name;     // A játékos neve
    private double points;   // A játékos által szerzett pontok
    private int wins;        // A játékos által nyert meccsek száma

    /**
     * Konstruktor, amely inicializálja a játékos eredményeit.
     *
     * @param name  A játékos neve.
     * @param points A játékos által szerzett pontok.
     * @param wins A játékos által nyert meccsek száma.
     */
    public PlayerResult(String name, double points, int wins) {
        this.name = name;
        this.points = points;
        this.wins = wins;
    }

    /**
     * Lekéri a játékos nevét.
     *
     * @return A játékos neve.
     */
    public String getName() {
        return name;
    }

    /**
     * Lekéri a játékos által szerzett pontokat.
     *
     * @return A játékos pontszáma.
     */
    public double getPoints() {
        return points;
    }

    /**
     * Lekéri a játékos által nyert meccsek számát.
     *
     * @return A játékos nyert meccseinek száma.
     */
    public int getWins() {
        return wins;
    }
}
