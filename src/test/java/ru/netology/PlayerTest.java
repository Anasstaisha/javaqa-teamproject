package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PlayerTest {

    private GameStore store = new GameStore();
    private Game game1 = store.publishGame("Game 1", "Genre 1");
    private Game game2 = store.publishGame("Game 2", "Genre 2");
    private Game game3 = store.publishGame("Game 3", "Genre 2");
    private Player player = new Player("Petya");

    @Test
    public void shouldPlay() {
        player.installGame(game1);

        int expected = 3;
        int actual = player.play(game1, 3);
        assertEquals(expected, actual);
    }

    @Test
    public void shouldPlayIfNoGame() {
        player.installGame(game1);

        Assertions.assertThrows(RuntimeException.class, () -> {
            player.play(game2, 6);
        });
    }

    @Test
    public void shouldPlayIfHoursNegative() {
        player.installGame(game1);

        Assertions.assertThrows(RuntimeException.class, () -> {
            player.play(game1, -6);
        });
    }

    @Test
    public void shouldSumGenreIfOneGame() {
        player.installGame(game1);
        player.play(game1, 3);

        int expected = 3;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfNoGame() {
        int expected = 0;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenrePositive() {
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 6);
        player.play(game2, 16);
        player.play(game3, 6);

        int expected = 22;
        int actual = player.sumGenre(game2.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfNotFindGenre() {
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 6);
        player.play(game2, 16);
        player.play(game3, 6);

        int expected = 0;
        int actual = player.sumGenre("Genre 3");
        assertEquals(expected, actual);
    }

    @Test
    public void shouldSumGenreIfNull() {
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 6);
        player.play(game2, 16);
        player.play(game3, 6);

        int expected = 0;
        int actual = player.sumGenre(null);
        assertEquals(expected, actual);
    }


    @Test
    public void shouldPlayerByGenreIfOneGameInstall() {
        player.installGame(game1);
        player.play(game1, 6);

        Game expected = game1;
        Game actual = player.mostPlayerByGenre(game1.getGenre());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldPlayerByGenreIfNoGameInstall() {

        Assertions.assertEquals(null, player.mostPlayerByGenre(game1.getGenre()));
    }

    @Test
    public void shouldPlayerByGenrePositiveIfOneBestGame() {
        player.installGame(game1);
        player.play(game1, 6);
        player.installGame(game2);
        player.play(game2, 16);
        player.installGame(game3);
        player.play(game3, 6);

        Game expected = game2;
        Game actual = player.mostPlayerByGenre(game2.getGenre());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldPlayerByGenrePositiveIfNoOneBestGame() {
        player.installGame(game1);
        player.installGame(game3);
        player.installGame(game2);
        player.play(game1, 6);
        player.play(game3, 16);
        player.play(game2, 16);

        Game expected = game3;
        Game actual = player.mostPlayerByGenre(game3.getGenre());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldPlayerByGenreIfMostHoursIsOne() {
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 0);
        player.play(game2, 0);
        player.play(game3, 1);

        Game expected = game3;
        Game actual = player.mostPlayerByGenre(game3.getGenre());
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void shouldPlayerByGenreIfNotFindGenre() {
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 6);
        player.play(game2, 16);
        player.play(game3, 6);

        Assertions.assertEquals(null, player.mostPlayerByGenre("Genre 3"));
    }

    @Test
    public void shouldPlayerByGenreIfGenreIsNull() {
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 6);
        player.play(game2, 16);
        player.play(game3, 6);

        Assertions.assertEquals(null, player.mostPlayerByGenre(null));
    }
}
