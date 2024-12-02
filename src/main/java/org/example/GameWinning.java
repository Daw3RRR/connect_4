package org.example;

/**
 * A játék győzelmi feltételeinek ellenőrzésére szolgáló osztály.
 * Ellenőrzi a győzelmet a sorokban, oszlopokban és átlókban a játéktáblán.
 */
public class GameWinning {
    private Table table; // A játék jelenlegi állapotát reprezentáló tábla.

    /**
     * Létrehoz egy GameWinning objektumot a megadott játéktáblával.
     *
     * @param table a játék tábla, amelyet értékelni kell
     */
    public GameWinning(Table table) {
        this.table = table;
    }

    /**
     * Ellenőrzi, hogy az adott karakter (pl. játékos vagy AI) teljesítette-e
     * a győzelmi feltételeket.
     *
     * @param character a vizsgált karakter (pl. "X" vagy "O")
     * @return true, ha a karakter nyert, különben false
     */
    public boolean checkWin(String character) {
        return checkRows(character) || checkColumns(character) || checkDiagonals(character);
    }

    /**
     * Ellenőrzi, hogy az adott karakter győzött-e valamelyik sorban.
     *
     * @param character a vizsgált karakter
     * @return true, ha a karakter győzelmet ért el egy sorban, különben false
     */
    private boolean checkRows(String character) {
        for (int row = 0; row < table.tabla.length; row++) {
            for (int col = 0; col < table.tabla[row].length - 3; col++) {
                if (table.tabla[row][col].equals(character) &&
                        table.tabla[row][col + 1].equals(character) &&
                        table.tabla[row][col + 2].equals(character) &&
                        table.tabla[row][col + 3].equals(character)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Ellenőrzi, hogy az adott karakter győzött-e valamelyik oszlopban.
     *
     * @param character a vizsgált karakter
     * @return true, ha a karakter győzelmet ért el egy oszlopban, különben false
     */
    private boolean checkColumns(String character) {
        for (int col = 0; col < table.tabla[0].length; col++) {
            for (int row = 0; row < table.tabla.length - 3; row++) {
                if (table.tabla[row][col].equals(character) &&
                        table.tabla[row + 1][col].equals(character) &&
                        table.tabla[row + 2][col].equals(character) &&
                        table.tabla[row + 3][col].equals(character)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Ellenőrzi, hogy az adott karakter győzött-e valamelyik átlóban.
     *
     * @param character a vizsgált karakter
     * @return true, ha a karakter győzelmet ért el egy átlóban, különben false
     */
    private boolean checkDiagonals(String character) {
        // Balról jobbra lefelé tartó átlók ellenőrzése
        for (int row = 0; row < table.tabla.length - 3; row++) {
            for (int col = 0; col < table.tabla[row].length - 3; col++) {
                if (table.tabla[row][col].equals(character) &&
                        table.tabla[row + 1][col + 1].equals(character) &&
                        table.tabla[row + 2][col + 2].equals(character) &&
                        table.tabla[row + 3][col + 3].equals(character)) {
                    return true;
                }
            }
        }

        // Jobbról balra lefelé tartó átlók ellenőrzése
        for (int row = 3; row < table.tabla.length; row++) {
            for (int col = 0; col < table.tabla[row].length - 3; col++) {
                if (table.tabla[row][col].equals(character) &&
                        table.tabla[row - 1][col + 1].equals(character) &&
                        table.tabla[row - 2][col + 2].equals(character) &&
                        table.tabla[row - 3][col + 3].equals(character)) {
                    return true;
                }
            }
        }

        return false;
    }
}
