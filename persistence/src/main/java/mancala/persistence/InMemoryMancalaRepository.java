package mancala.persistence;

import mancala.domain.IMancala;

import java.util.HashMap;

public class InMemoryMancalaRepository implements IMancalaRepository {
    private final HashMap<String, IMancala> savedGames = new HashMap<>();

    @Override
    public void save(String key, IMancala game) {
        savedGames.put(key, game);
    }

    @Override
    public IMancala get(String key) {
        return savedGames.get(key);
    }
}
