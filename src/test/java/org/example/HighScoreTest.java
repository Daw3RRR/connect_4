package org.example;

import org.example.HighScore;
import org.example.PlayerResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class HighScoreTest {

    private HighScore highScore;
    private DatabaseManager dbManagerMock;

    @BeforeEach
    public void setUp() {
        dbManagerMock = Mockito.mock(DatabaseManager.class);
        highScore = new HighScore();
        highScore.dbManager = dbManagerMock;
    }

    @Test
    public void testDisplayTop3_EmptyDatabase() {
        when(dbManagerMock.getAllPlayersResults()).thenReturn(new ArrayList<>());

        // A konzol kimenetét itt ellenőrizhetnénk, ha szükséges
        highScore.displayTop3();

        // Ellenőrizzük, hogy a dbManager.getAllPlayersResults() metódus meghívásra került
        verify(dbManagerMock, times(1)).getAllPlayersResults();
    }

    @Test
    public void testDisplayTop3_SinglePlayer() {
        List<PlayerResult> players = new ArrayList<>();
        players.add(new PlayerResult("John Doe", 100, 1));
        when(dbManagerMock.getAllPlayersResults()).thenReturn(players);

        // A konzol kimenetét itt ellenőrizhetnénk, ha szükséges
        highScore.displayTop3();

        // Ellenőrizzük, hogy a dbManager.getAllPlayersResults() metódus meghívásra került
        verify(dbManagerMock, times(1)).getAllPlayersResults();
    }

    @Test
    public void testDisplayTop3_MultiplePlayers() {
        List<PlayerResult> players = new ArrayList<>();
        players.add(new PlayerResult("Alice", 150, 2));
        players.add(new PlayerResult("Bob", 100, 5));
        players.add(new PlayerResult("Charlie", 200, 3));
        when(dbManagerMock.getAllPlayersResults()).thenReturn(players);

        // A konzol kimenetét itt ellenőrizhetnénk, ha szükséges
        highScore.displayTop3();

        // Ellenőrizzük, hogy a dbManager.getAllPlayersResults() metódus meghívásra került
        verify(dbManagerMock, times(1)).getAllPlayersResults();
    }

    @Test
    public void testDisplayTop3_LessThenThreePlayers() {
        List<PlayerResult> players = new ArrayList<>();
        players.add(new PlayerResult("Alice", 150, 2));
        players.add(new PlayerResult("Bob", 100, 5));
        when(dbManagerMock.getAllPlayersResults()).thenReturn(players);

        // A konzol kimenetét itt ellenőrizhetnénk, ha szükséges
        highScore.displayTop3();

        // Ellenőrizzük, hogy a dbManager.getAllPlayersResults() metódus meghívásra került
        verify(dbManagerMock, times(1)).getAllPlayersResults();
    }
}