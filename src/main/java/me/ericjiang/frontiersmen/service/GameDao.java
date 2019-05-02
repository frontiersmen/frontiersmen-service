package me.ericjiang.frontiersmen.service;

public interface GameDao {
    Game getGame(String gameId);
    void putGame(Game game);
    void updateGame(Game game);
}
