package org.example;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * A játék állapotának mentését, betöltését és törlését kezeli.
 * Lehetővé teszi a játéktábla és a játékos vagy az AI aktuális körének tárolását egy fájlba,
 * és később annak betöltését a játék folytatásához.
 */
public class GameSaveManager {

    private static final String SAVE_FILE = "game_save.txt";

    /**
     * Menteni a jelenlegi játék állapotát egy fájlba.
     *
     * @param board       a menteni kívánt játéktábla
     * @param isPlayerNext igaz, ha a játékos következik, hamis, ha az AI következik
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
     * Betölti a mentett játékot egy fájlból, ha az létezik.
     *
     * @param board      a játéktábla, amelyet ki kell tölteni
     * @param gameLogic  a játékmenet logikáját kezelő objektum, amely frissíti a köröket
     * @return igaz, ha a játék sikeresen be lett töltve, hamis, ha nem
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
     * Törli a mentett játék fájlt, ha az létezik.
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
