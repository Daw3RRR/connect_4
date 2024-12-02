package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Handles saving, loading, and deleting game states.
 * Allows storing the game board and player turn status in a file
 * and retrieving it later to resume the game.
 */
public class GameSaveManager {

    private static final String SAVE_FILE = "game_save.txt";

    /**
     * Saves the current game state to a file.
     *
     * @param board       the game board to save
     * @param isPlayerNext true if it's the player's turn, false if AI's turn
     */
    public void saveGame(String[][] board, boolean isPlayerNext) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(SAVE_FILE))) {
            for (String[] row : board) {
                writer.write(String.join(",", row));
                writer.newLine();
            }
            writer.write(isPlayerNext ? "PLAYER" : "AI");
            writer.newLine();
            System.out.println("Game saved successfully.");
        } catch (IOException e) {
            System.out.println("Failed to save the game: " + e.getMessage());
        }
    }

    /**
     * Loads a saved game from a file, if it exists.
     *
     * @param board      the game board to populate
     * @param gameLogic  the game logic object to update the turn
     * @return true if the game was successfully loaded, false otherwise
     */
    public boolean loadGame(String[][] board, GameLogic gameLogic) {
        File saveFile = new File(SAVE_FILE);
        if (!saveFile.exists()) {
            System.out.println("No saved game found. Starting a new game.");
            return false;
        }

        try (
                FileInputStream fis = new FileInputStream(saveFile);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader reader = new BufferedReader(isr)
        ) {
            for (int i = 0; i < board.length; i++) {
                String[] row = reader.readLine().split(",");
                System.arraycopy(row, 0, board[i], 0, row.length);
            }
            String nextTurn = reader.readLine();
            gameLogic.setPlayerNext("PLAYER".equals(nextTurn));
            System.out.println("Game loaded successfully.");
            return true;
        } catch (IOException e) {
            System.out.println("Failed to load the game: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes the saved game file, if it exists.
     */
    public void deleteSave() {
        File file = new File(SAVE_FILE);
        if (file.exists() && file.delete()) {
            System.out.println("Saved game deleted.");
        } else {
            System.out.println("No saved game to delete.");
        }
    }
}
