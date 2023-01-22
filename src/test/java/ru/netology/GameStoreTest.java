package ru.netology;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameStoreTest {

    private GameStore store = new GameStore();
    private Game game1 = store.publishGame("Game 1", "Genre 1");
    private Game game2 = store.publishGame("Game 2", "Genre 2");
    private Player player1 = new Player("Olya");
    private Player player2 = new Player("Misha");
    private Player player3 = new Player("Anya");


    @Test
    public void shouldAddGame() {

        Assertions.assertTrue(store.containsGame(game2));
    }

    @Test
    public void shouldNotAddGameIfNull() {

        Assertions.assertFalse(store.containsGame(null));
    }

    @Test
    public void shouldShowPlayerMostHours() {
        store.addPlayTime("Anya", 3);
        store.addPlayTime("Misha", 2);
        store.addPlayTime("Olya", 1);

        String expected = "Anya";
        String actual = store.getMostPlayer();


        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldShowPlayerMostHoursIfOnePlayer() {
        store.addPlayTime("Olya", 5);
        store.addPlayTime("Olya", 3);
        store.addPlayTime("Olya", 7);

        String expected = "Olya";
        String actual = store.getMostPlayer();


        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldAddPlayedTime() {
        store.addPlayTime("Misha", 7);
        store.addPlayTime("Anya", 1);
        store.addPlayTime("Anya", 7);

        String expected = "Anya";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldChooseMostTimePlayer() {
        store.addPlayTime("Misha", 0);
        store.addPlayTime("Anya", 1);

        String expected = "Anya";
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNullIfNoPlayers() {
        String expected = null;
        String actual = store.getMostPlayer();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSumPlayedTime() {
        store.addPlayTime("Misha", 7);
        store.addPlayTime("Anya", 3);
        store.addPlayTime("Olya", 10);

        int expected = 20;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldSumPlayedTimeIfOnePlayer() {
        store.addPlayTime("Misha", 2);
        store.addPlayTime("Misha", 0);
        store.addPlayTime("Misha", 17);

        int expected = 19;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldNotSumPlayedTimeIfNoPlayers() {
        store.addPlayTime(null, 7);
        store.addPlayTime(null, 3);
        store.addPlayTime(null, 10);

        int expected = 0;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldZeroIfNoPlayedTime() {
        store.addPlayTime("Anya", 0);
        store.addPlayTime("Misha", 0);
        store.addPlayTime("Olya", 0);

        int expected = 0;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldZeroPlayedTimeIfNoPlayers() {

        int expected = 0;
        int actual = store.getSumPlayedTime();

        Assertions.assertEquals(expected, actual);
    }


}
