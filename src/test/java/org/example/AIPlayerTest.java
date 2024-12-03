package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tesztosztály az AIPlayer osztályhoz.
 */
public class AIPlayerTest {

    /**
     * Teszteli, hogy az AIPlayer által választott oszlop mindig 0 és 6 között van.
     */
    @Test
    public void testChooseColumn() {
        AIPlayer aiPlayer = new AIPlayer();

        // Többször futtatjuk a választást, hogy biztosítsuk a véletlenszerűség érvényes tartományát
        for (int i = 0; i < 100; i++) {
            int column = aiPlayer.chooseColumn();
            assertTrue(column >= 0 && column <= 6,
                    "AIPlayer választott egy érvénytelen oszlopot: " + column);
        }
    }
}
