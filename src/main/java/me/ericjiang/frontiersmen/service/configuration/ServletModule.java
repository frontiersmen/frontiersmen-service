package me.ericjiang.frontiersmen.service.configuration;

import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import me.ericjiang.frontiersmen.service.dao.GameDao;
import me.ericjiang.frontiersmen.service.dao.GameDaoDynamoDB;
import me.ericjiang.frontiersmen.service.gamemaster.MessageEventProcessor;
import me.ericjiang.frontiersmen.service.model.Game;
import me.ericjiang.frontiersmen.service.model.GameEvent;

import java.util.function.BiConsumer;

public class ServletModule extends com.google.inject.servlet.ServletModule {
    @Override
    protected void configureServlets() {
        // provide injector to EndpointConfigurator - https://stackoverflow.com/a/32053412/6497736
        requestStaticInjection(EndpointConfigurator.class);

        // configure GameDao
        bind(GameDao.class).to(GameDaoDynamoDB.class);

        // configure GameMaster's event processors
        MapBinder<String, BiConsumer<GameEvent, Game>> eventProcessors = MapBinder.newMapBinder(
                binder(),
                new TypeLiteral<String>() {},
                new TypeLiteral<BiConsumer<GameEvent, Game>>() {});
        eventProcessors.addBinding("MessageEvent").to(MessageEventProcessor.class);
    }
}
