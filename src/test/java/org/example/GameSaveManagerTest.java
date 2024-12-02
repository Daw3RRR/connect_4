package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class GameSaveManagerTest {
    private static final String SAVE_FILE = "game_save.txt";

    @BeforeEach
    public void setup() {
        // Töröld a mentési fájlt, ha létezik
        File file = new File(SAVE_FILE);
        if (file.exists()) {
            file.delete();
        }
    }


    @Test
    public void testSaveGame() {
        // Simulate console output capture
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create mock game board data
        String[][] board = {{"X", "O", "-"}, {"-", "-", "-"}, {"-", "-", "-"}};

        // Create a GameSaveManager instance
        GameSaveManager gameSaveManager = new GameSaveManager();

        // Call saveGame without user input
        gameSaveManager.saveGame(board, true);

        // Verify successful save message in console output
        String output = outputStream.toString();
        assertTrue(output.contains("Game saved successfully."), "Console output should contain success message");
    }
/*
    @Test
public void testLoadGame_NoSaveFile() {
    // Capture console output
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outputStream));

    // Create mock game board
    String[][] board = new String[3][3];

    // Create a mock GameLogic object
        GameLogic gameLogic = new GameLogic() {
            @Override
            public void setPlayerNext(boolean isPlayerNext) {
                // Szimuláld a GameLogic viselkedését:
                // Például nyomtasd ki a paraméter értékét a konzolra
                System.out.println("Player's turn: " + isPlayerNext);
            }
        };

    // Create a GameSaveManager instance
    GameSaveManager gameSaveManager = new GameSaveManager();

    // Call loadGame simulating no save file
    boolean loaded = gameSaveManager.loadGame(board, gameLogic);

    // Assert game not loaded and console output reflects that
    String output = outputStream.toString();
    assertFalse(loaded, "Game should not be loaded");
    assertTrue(output.contains("No saved game found. Starting a new game."),
               "Console output should indicate missing save file: " + output);
}
*/
    @Test
    public void testDeleteSave_NoFile() {
        // Capture console output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        // Create a GameSaveManager instance
        GameSaveManager gameSaveManager = new GameSaveManager();

        // Call deleteSave simulating no save file
        gameSaveManager.deleteSave();

        // Assert console output indicates no save file to delete
        String output = outputStream.toString();
        assertTrue(output.contains("No saved game to delete."), "Console output should indicate no save file to delete");
    }
}