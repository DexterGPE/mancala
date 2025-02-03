package mancala.persistence;


import mancala.domain.IMancala;
import mancala.domain.Mancala;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class InMemoryMancalaRepositoryTest {
    @Test
    public void saveTest() {
        InMemoryMancalaRepository db = new InMemoryMancalaRepository();
        // Assuming save() is a method in InMemoryMancalaRepository
        String gameId = "game123";
        db.save(gameId, new Mancala("testName1", "test2")); // Assuming MancalaGame is the game object
        IMancala getGame = db.get(gameId);

        assertEquals(getGame.getNameOfPlayerOne(), "testName1");
        assertEquals(getGame.getNameOfPlayerTwo(), "test2");
    }
}
