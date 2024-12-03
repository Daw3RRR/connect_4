package org.example;

import org.example.PlayerResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerResultTest {

    @Test
    public void testConstructor() {
        PlayerResult player = new PlayerResult("John Doe", 100.5, 5);
        assertEquals("John Doe", player.getName());
        assertEquals(100.5, player.getPoints());
        assertEquals(5, player.getWins());
    }

    @Test
    public void testNegativePoints() {
        // Bár a konstruktorban nincs explicit ellenőrzés, érdemes tesztelni, hogy mi történik negatív pontszámmal
        PlayerResult player = new PlayerResult("Jane Smith", -50, 3);
        assertEquals(-50, player.getPoints()); // Ha a konstruktor nem dob kivételt, akkor a pont értékét el kell fogadnia
    }
}
