package org.example;

/**
 * Az alkalmazás belépési pontja.
 * Ez az osztály tartalmazza a `main` metódust, ahonnan az alkalmazás futása elindul.
 */
public class App { // elkészítem az App osztályt

    /**
     * A `main` metódus, ahonnan az alkalmazás futása elindul.
     * Ez a kiindulópontja az alkalmazásnak.
     */
    public static void main(String[] args) {

        // Új `Player` objektum létrehozása
        Player player = new Player();
        // Ez az osztály kezeli a játékos adatait és attribútumait
        // (például név, pontszám).

        // Új `GameLogic` objektum létrehozása
        GameLogic gameLogic = new GameLogic();
        // A GameLogic osztály irányítja a játék szabályait és folyamatait.
        // Ő kezeli a játék fő logikáját.

        // Adatbázis kezelésére szolgáló objektum létrehozása,
        // amely automatikusan inicializálja az adatbázis táblát
        DatabaseManager databaseManager = new DatabaseManager();
        // Ez az osztály felelős az adatbázis-kapcsolatért,
        // a tábla létrehozásáért, és az adatok hozzáadásáért vagy lekérdezéséért.

        // Játék elindítása (ismét GameLogic példányt hoz létre,
        // valószínűleg duplikáció)
        GameLogic game = new GameLogic();
        // Ez a sor egy új `GameLogic` objektumot hoz létre,
        // amely ugyanazt a célt szolgálja, mint az előző `gameLogic` példány.
    }
}
