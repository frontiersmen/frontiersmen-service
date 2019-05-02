package me.ericjiang.frontiersmen.service.configuration;

import com.google.inject.AbstractModule;

import me.ericjiang.frontiersmen.service.GameDao;
import me.ericjiang.frontiersmen.service.GameDaoDynamoDB;

public class GameEndpointModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(GameDao.class).to(GameDaoDynamoDB.class);
    }
}
