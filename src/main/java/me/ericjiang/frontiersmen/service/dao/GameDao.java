package me.ericjiang.frontiersmen.service.dao;

import me.ericjiang.frontiersmen.service.model.Game;

public interface GameDao {
    Game getGame(String gameId);
    void putGame(Game game);
    void updateGame(Game game);
}
