package org.example;

import java.util.Scanner; // A felhasználói bemenet kezeléséhez szükséges osztály

/**
 * A játék logikáját megvalósító osztály.
 * Kezeli a játék indítását, a játékos és AI lépéseit, valamint a győzelmeket és mentést.
 */
public class GameLogic {
    private DatabaseManager dbManager; // Az adatbázis kezeléséért felelős példány
    public boolean isPlayerNext = true; // Annak nyilvántartása, hogy a játékos következik-e
    public static boolean gameStarted = false; // A játék indításának állapota
    private AIPlayer aiPlayer; // A gépi játékos objektum
    public Player player; // A felhasználói játékos objektum
    public String playerChar = "S"; // A játékos karaktere (pl. "S" az oszlopban)
    public String aiChar = "P"; // A gép karaktere (pl. "P" az oszlopban)
    public Table table; // A játéktábla objektum
    public int startingQ; // A játék indítási lehetőségének beállítása
    private GameSaveManager saveManager = new GameSaveManager(); // A játék mentéséért felelős objektum
    private HighScore highScore; // A magas pontszámokat kezelő objektum

    /**
     * Konstruktor: az osztály inicializálása és a játékos név bekérése,
     * valamint a játék indítása.
     */
    public GameLogic() {
        dbManager = new DatabaseManager(); // Adatbázis-kezelő inicializálása
        aiPlayer = new AIPlayer(); // Gépi játékos inicializálása
        player = new Player(); // Játékos inicializálása
        highScore = new HighScore(); // HighScore példány inicializálása

        // Ha a játékos neve még nem lett megadva, kérjük be a nevét
        if (player.name == null || player.name.isEmpty()) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("What is your name?");
            player.name = scanner.nextLine(); // A játékos nevének beolvasása
            System.out.println("Welcome " + player.name);
        }

        // A játék indítása
        gameStarter();

        // Ha a játék elindult, belépünk a fő ciklusba
        if (gameStarted) {
            startGameLoop();
        }
    }

    /**
     * Visszaadja, hogy a játékos következik-e.
     *
     * @return true, ha a játékos következik, false, ha az AI következik
     */
    public boolean isPlayerNext() {
        return isPlayerNext;
    }

    /**
     * Beállítja, hogy a játékos vagy az AI következik.
     *
     * @param isPlayerNext true, ha a játékos következik, false, ha az AI következik
     */
    public void setPlayerNext(boolean isPlayerNext) {
        this.isPlayerNext = isPlayerNext;
    }

    /**
     * A játék indításának lehetőségeit biztosítja és kezeli.
     * A felhasználó dönthet, hogy új játékot indít, betölt egy mentett játékot, vagy megtekinti a legjobb eredményeket.
     */
    public void gameStarter() {
        Scanner scanner = new Scanner(System.in);
        while (true) { // Végtelen ciklus a helyes választásig
            System.out.println("Ready to start?\n" +
                    "1. Yes\n" +
                    "2. No\n" +
                    "3. Load saved game\n" +
                    "4. View HighScore"); // Játék indítási lehetőségei
            try {
                startingQ = scanner.nextInt(); // Felhasználó választásának beolvasása
                scanner.nextLine();
                if (startingQ == 1) {
                    table = new Table(); // Új játék indítása, tábla inicializálása
                    gameStarted = true; // A játék elindul
                    break; // Kilépés a ciklusból
                } else if (startingQ == 2) {
                    // Ha a felhasználó nem akar játszani, kilépünk
                    System.out.println("Thank you for starting the program, have a nice day!\n");
                    System.out.println("The game is closing...");
                    gameStarted = false;
                    break;
                } else if (startingQ == 3) {
                    // Mentett játék betöltése
                    table = new Table(); // Új tábla inicializálása a betöltött játékhoz
                    if (saveManager.loadGame(table.tabla, this)) {
                        gameStarted = true; // Ha sikeres a betöltés, elindítjuk a játékot
                        break;
                    } else {
                        System.out.println("No saved game found. Starting a new game.");
                    }
                } else if (startingQ == 4) {
                    // A HighScore megtekintése
                    highScore.displayTop3();
                } else {
                    // Hibás bemenet esetén figyelmeztetés
                    System.out.println("Invalid option. Please enter 1, 2, 3, or 4.");
                }
            } catch (Exception e) {
                // Ha nem számot ad meg a felhasználó, figyelmeztetjük
                System.out.println("Invalid input. Please enter a number (1, 2, 3, or 4).");
                scanner.next(); // Hibás bemenet eldobása
            }
        }
    }

    /**
     * A játék fő ciklusa. Kezeli a játékos és az AI lépéseit,
     * ellenőrzi a győzelmet, valamint menti az állapotot minden lépés után.
     */
    public void startGameLoop() {
        System.out.println("\n");
        Scanner scanner = new Scanner(System.in);
        // A győzelem ellenőrzését végző objektum
        GameWinning gameWinning = new GameWinning(table);

        while (gameStarted) { // A játék addig fut, amíg nincs vége
            table.displayTable(); // A tábla megjelenítése a játékosnak

            if (isPlayerNext) {
                // A játékos lépése
                System.out.println("\nEnter column (0-6) to place your character: ");
                int column = scanner.nextInt();

                // A karakter elhelyezése az oszlopban
                if (!table.placeCharacter(column, playerChar)) {
                    // Ha az oszlop foglalt vagy érvénytelen, figyelmeztetjük
                    System.out.println("Invalid move. Try again.");
                } else {
                    // Ellenőrizzük, hogy a játékos nyert-e
                    if (gameWinning.checkWin(playerChar)) {
                        table.displayTable(); // Nyertes állapot megjelenítése
                        System.out.println("Congratulations! You have won!");
                        playerWins(); // A játékos győzelmének rögzítése
                        gameStarted = false; // A játék véget ér
                        break;
                    }
                    isPlayerNext = false; // Váltás az AI-ra

                    // Az AI lépése
                    int aiColumn = aiPlayer.chooseColumn(); // Az AI választ egy oszlopot
                    if (table.placeCharacter(aiColumn, aiChar)) {
                        // Ellenőrizzük, hogy az AI nyert-e
                        if (gameWinning.checkWin(aiChar)) {
                            table.displayTable(); // Nyertes állapot megjelenítése
                            System.out.println("AI has won! Better luck next time.");
                            aiWins(); // Az AI győzelmének rögzítése
                            gameStarted = false; // A játék véget ér
                            break;
                        }
                        isPlayerNext = true; // Visszaváltunk a játékosra
                    } else {
                        // Ha az AI nem tud lépni
                        System.out.println("AI chose an invalid move. It will try again.");
                    }
                }
            }

            // A játék állapotának mentése minden lépés után
            saveManager.saveGame(table.tabla, isPlayerNext);
        }
    }

    /**
     * A játékos győzelmének kezelése, beleértve a pontszám hozzáadását az adatbázisba.
     */
    public void playerWins() {
        String playerName = player.name; // A játékos neve
        double points = 1.0; // Győzelemért járó pontszám
        dbManager.addResult(playerName, points); // Az eredmény rögzítése az adatbázisban
        System.out.println("Player's score updated in the database.");
    }

    /**
     * Az AI győzelmének kezelése, beleértve a pontszám hozzáadását az adatbázisba.
     */
    public void aiWins() {
        String aiName = "AI"; // Az AI neve
        double points = 1.0; // Győzelemért járó pontszám
        dbManager.addResult(aiName, points); // Az AI eredményének rögzítése az adatbázisban
        System.out.println("AI's score updated in the database.");
    }
}
