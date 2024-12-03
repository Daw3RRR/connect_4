package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tesztosztály a Table osztályhoz.
 */
public class TableTest {

    /**
     * Teszteli, hogy a konstruktor helyesen inicializálja-e a táblát.
     */
    @Test
    public void testTableInitialization() {
        Table table = new Table();

        // Ellenőrizzük, hogy minden mező "*" karaktert tartalmaz
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                assertEquals("*", table.tabla[i][j], "A tábla mezője nem üresen lett inicializálva!");
            }
        }
    }

    /**
     * Teszteli, hogy egy karakter helyesen kerül elhelyezésre a táblán.
     */
    @Test
    public void testPlaceCharacter() {
        Table table = new Table();

        boolean result = table.placeCharacter(3, "X");
        assertTrue(result, "A karakter elhelyezése sikertelen!");
        assertEquals("X", table.tabla[5][3], "A karakter nem a várt helyre került!");
    }

    /**
     * Teszteli, hogy egy karakter nem kerülhet érvénytelen oszlopba.
     */
    @Test
    public void testPlaceCharacterInvalidColumn() {
        Table table = new Table();

        boolean result = table.placeCharacter(-1, "X");
        assertFalse(result, "Érvénytelen oszlop esetén nem kellene elhelyezni a karaktert!");

        result = table.placeCharacter(7, "X");
        assertFalse(result, "Érvénytelen oszlop esetén nem kellene elhelyezni a karaktert!");
    }

    /**
     * Teszteli, hogy egy karakter nem helyezhető el egy tele oszlopba.
     */
    @Test
    public void testPlaceCharacterFullColumn() {
        Table table = new Table();

        // Töltsük meg az oszlopot karakterekkel
        for (int i = 0; i < 6; i++) {
            boolean result = table.placeCharacter(2, "O");
            assertTrue(result, "Az oszlopba nem sikerült karaktert elhelyezni!");
        }

        // Próbáljuk meg elhelyezni egy újabb karaktert ugyanabban az oszlopban
        boolean result = table.placeCharacter(2, "X");
        assertFalse(result, "Tele oszlopba nem kellene karaktert elhelyezni!");
    }
}
