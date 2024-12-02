
package org.example;

/**
 * A játéktáblát reprezentáló osztály.
 * Tartalmazza a tábla inicializálását, karakterek elhelyezését és a tábla megjelenítését.
 */
public class Table {

    String[][] tabla = new String[6][7]; // A játéktábla 6 sorból és 7 oszlopból áll

    /**
     * Konstruktor, amely inicializálja a táblát üres mezőkkel ("*").
     * A tábla létrehozása után az aktuális állapotot megjeleníti.
     */
    public Table() {
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {
                tabla[i][j] = "*"; // Üres helyek inicializálása
            }
        }
        displayTable(); // Megjelenítjük a kezdeti táblát
    }

    /**
     * Egy karakter elhelyezése az adott oszlopban.
     *
     * @param column   Az oszlop indexe, ahová a karaktert elhelyezzük (0-tól 6-ig).
     * @param character A karakter, amelyet elhelyezünk a táblán.
     * @return Igaz, ha sikerült elhelyezni a karaktert, hamis, ha az oszlop tele van vagy érvénytelen.
     */
    public boolean placeCharacter(int column, String character) {
        // Ellenőrizzük, hogy az oszlop érvényes-e
        if (column < 0 || column >= tabla[0].length) {
            System.out.println("Invalid column. Please choose a column between 0 and 6.");
            return false;
        }
        // Megkeressük a legalsó üres helyet az adott oszlopban
        for (int row = tabla.length - 1; row >= 0; row--) {
            if (tabla[row][column].equals("*")) { // Ha üres helyet találunk
                tabla[row][column] = character;  // Elhelyezzük a játékos karakterét
                return true;    // Sikeres elhelyezés
            }
        }
        System.out.println("Column is full! Choose another column.");
        return false; // Az oszlop tele van
    }

    /**
     * A játéktábla aktuális állapotának megjelenítése a konzolon.
     */
    public void displayTable() {
        for (int i = 0; i < tabla.length; i++) {
            for (int j = 0; j < tabla[i].length; j++) {
                System.out.print(tabla[i][j] + " "); // A tábla egy sorának kiírása
            }
            System.out.println(); // Új sor kezdése
        }
    }
}
