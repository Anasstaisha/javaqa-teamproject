package ru.netology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {

    private GameStore store = new GameStore();
    private Game game1 = store.publishGame("Game 1", "Genre 1");
    private Game game2 = store.publishGame("Game 2", "Genre 2");
    private Game game3 = store.publishGame("Game 3", "Genre 2");
    private Player player = new Player("Petya");

    @Test
    public void shouldSumGenreIfOneGame() {
        player.installGame(game1);
        player.play(game1, 3);

        int expected = 3;
        int actual = player.sumGenre(game1.getGenre());
        assertEquals(expected, actual);
    }

    @Test
    public void shouldPlayUninstalledGame (){
        player.installGame(game1);

        Assertions.assertThrows(RuntimeException.class, () -> {
            player.play(game2, 6);
        });
    }

    @Test
    public void shouldPlayerByGenrePositive() {
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 6);
        player.play(game2, 16);
        player.play(game3, 6);

        Assertions.assertEquals(game2, player.mostPlayerByGenre("Genre 2"));
    }

    @Test
    public void shouldPlayerByGenreNoExist() {
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 6);
        player.play(game2, 16);
        player.play(game3, 6);

        Assertions.assertEquals(null, player.mostPlayerByGenre("Genre 3"));
    }

    @Test
    public void shouldPlayerByGenreNull() {
        player.installGame(game1);
        player.installGame(game2);
        player.installGame(game3);
        player.play(game1, 6);
        player.play(game2, 16);
        player.play(game3, 6);

        Assertions.assertEquals(null, player.mostPlayerByGenre(null));
    }
}
