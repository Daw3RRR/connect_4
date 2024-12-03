package org.example;

import java.util.Random;

/**
 * Ez az osztály felelős az AI játékos viselkedéséért.
 * Tartalmazza az AI lépésének logikáját a játékban.
 */
public class AIPlayer { // AI játékos létrehozása
    private Random random = new Random();

    /**
     * Meghatározza az AI következő lépését egy véletlenszerű oszlop kiválasztásával.
     * Az oszlopot véletlenszerűen választja ki 0 és 6 között (beleértve).
     *
     * @return Az oszlop indexe (0-6), ahová az AI leejti a karakterét.
     */
    public int chooseColumn() {
        return random.nextInt(7); // Választ egy véletlenszerű oszlopot 0 és 6 között
    }
}
