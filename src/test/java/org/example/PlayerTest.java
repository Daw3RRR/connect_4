package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tesztek a Player osztályhoz.
 */
class PlayerTest {

    @Test
    void testDefaultPlayerNameIsNull() {
        // Létrehozunk egy Player példányt
        Player player = new Player();

        // Ellenőrizzük, hogy a name mező alapértelmezett értéke null
        assertNull(player.name, "A névnek alapértelmezetten nullnak kell lennie.");
    }

    @Test
    void testSetPlayerName() {
        // Létrehozunk egy Player példányt
        Player player = new Player();

        // Beállítjuk a játékos nevét
        String testName = "Teszt Játékos";
        player.name = testName;

        // Ellenőrizzük, hogy a név helyesen van-e beállítva
        assertEquals(testName, player.name, "A név nem egyezik a várt értékkel.");
    }
}
