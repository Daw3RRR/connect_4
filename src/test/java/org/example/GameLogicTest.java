package org.example;


import org.example.GameLogic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class GameLogicTest {

  /**  @Test
    void testGameStarter_NewGame() {
        // Előkészítés: A rendszerbemenet átállítása egy "1" értéket tartalmazó stream-re
        ByteArrayInputStream in = new ByteArrayInputStream("1\n".getBytes());
        System.setIn(in);

        // Tesztelendő metódus meghívása
        GameLogic gameLogic = new GameLogic();

        // Ellenőrzések
        assertTrue(gameLogic.gameStarted);
        // ... további ellenőrzések a tábla inicializálására, stb.
    }
*/
    @Test
    void testGameStarter_LoadGame_Success() {
        // ... hasonlóan az előző teszthez, de "3" értékkel a bemenetben
        // A saveManager.loadGame metódusát megfelelően kell mockolni vagy stubolni
    }

    @Test
    void testGameStarter_LoadGame_Failure() {
        // ... hasonlóan az előző teszthez, de a saveManager.loadGame metódusát úgy állítjuk be, hogy false-t adjon vissza
    }

    @Test
    void testPlayerWins() {
        // ... a playerWins metódus tesztelése, pl. ellenőrizzük, hogy hívja-e a dbManager.addResult metódust a megfelelő paraméterekkel
    }

    // ... további tesztek a többi metódushoz: isPlayerNext, setPlayerNext, startGameLoop, stb.

    // Segítő metódus a konzol kimenetének összegyűjtéséhez
    private String getConsoleOutput(Runnable block) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        System.setOut(new PrintStream(out));
        try {
            block.run();
        } finally {
            System.setOut(originalOut);
        }
        return out.toString();
    }


    private ByteArrayOutputStream outputStream; // A konzol kimenetének rögzítéséhez
    private ByteArrayInputStream inputStream; // Szimulált bemenet

    @BeforeEach
    public void setup() {
        // Konzol kimenet elkapása
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
/*
    @Test
    public void testGameStarter_UserChoosesOption2() {
        // Szimulált bemenet a játékos nevéhez és az opcióhoz
        String simulatedInput = "John\n2\n"; // Játékos név: "John", opció: "2"
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        // Kimenet rögzítése
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // GameLogic példány
        new GameLogic();

        // Ellenőrzések
        String output = outputStream.toString();
        assertTrue(output.contains("Thank you for starting the program, have a nice day!"));
        assertTrue(output.contains("The game is closing..."));
    }
*/
}
