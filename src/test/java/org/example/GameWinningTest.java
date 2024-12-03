package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tesztosztály a GameWinning osztályhoz.
 */
public class GameWinningTest {

    /**
     * Teszteli, hogy a győzelmi feltételek helyesen kerülnek ellenőrzésre a sorokban.
     */
    @Test
    public void testCheckWinRows() {
        Table table = new Table();
        table.tabla[0][0] = "X";
        table.tabla[0][1] = "X";
        table.tabla[0][2] = "X";
        table.tabla[0][3] = "X"; // Sorban négy egymás mellett

        GameWinning gameWinning = new GameWinning(table);
        assertTrue(gameWinning.checkWin("X"), "A soros győzelmet nem észlelte.");
    }

    /**
     * Teszteli, hogy a győzelmi feltételek helyesen kerülnek ellenőrzésre az oszlopokban.
     */
    @Test
    public void testCheckWinColumns() {
        Table table = new Table();
        table.tabla[0][0] = "O";
        table.tabla[1][0] = "O";
        table.tabla[2][0] = "O";
        table.tabla[3][0] = "O"; // Oszlopban négy egymás alatt

        GameWinning gameWinning = new GameWinning(table);
        assertTrue(gameWinning.checkWin("O"), "Az oszlopos győzelmet nem észlelte.");
    }

    /**
     * Teszteli, hogy a győzelmi feltételek helyesen kerülnek ellenőrzésre az átlókban (balról jobbra).
     */
    @Test
    public void testCheckWinDiagonalLeftToRight() {
        Table table = new Table();
        table.tabla[0][0] = "X";
        table.tabla[1][1] = "X";
        table.tabla[2][2] = "X";
        table.tabla[3][3] = "X"; // Átló balról jobbra

        GameWinning gameWinning = new GameWinning(table);
        assertTrue(gameWinning.checkWin("X"), "A balról jobbra átlós győzelmet nem észlelte.");
    }

    /**
     * Teszteli, hogy a győzelmi feltételek helyesen kerülnek ellenőrzésre az átlókban (jobbról balra).
     */
    @Test
    public void testCheckWinDiagonalRightToLeft() {
        Table table = new Table();
        table.tabla[3][0] = "O";
        table.tabla[2][1] = "O";
        table.tabla[1][2] = "O";
        table.tabla[0][3] = "O"; // Átló jobbról balra

        GameWinning gameWinning = new GameWinning(table);
        assertTrue(gameWinning.checkWin("O"), "A jobbról balra átlós győzelmet nem észlelte.");
    }

    /**
     * Teszteli, hogy a győzelem feltételek hiányában nem ad pozitív választ.
     */
    @Test
    public void testNoWin() {
        Table table = new Table();
        GameWinning gameWinning = new GameWinning(table);
        assertFalse(gameWinning.checkWin("X"), "Helytelenül észlelte a győzelmet.");
        assertFalse(gameWinning.checkWin("O"), "Helytelenül észlelte a győzelmet.");
    }
}
