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
