package me.ericjiang.frontiersmen.service.dao;

import me.ericjiang.frontiersmen.service.model.Game;

public class GameDaoDynamoDB implements GameDao {
    @Override
    public Game getGame(String gameId) {
        return new Game("123");
    }

    @Override
    public void putGame(Game game) {

    }

    @Override
    public void updateGame(Game game) {

    }
}
