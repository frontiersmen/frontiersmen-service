package me.ericjiang.frontiersmen.service.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.inject.Provides;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.MapBinder;
import me.ericjiang.frontiersmen.service.dao.GameDao;
import me.ericjiang.frontiersmen.service.dao.GameDaoDynamoDB;
import me.ericjiang.frontiersmen.service.gamemaster.MessageEventProcessor;
import me.ericjiang.frontiersmen.service.model.Game;
import me.ericjiang.frontiersmen.service.model.event.GameEvent;
import me.ericjiang.frontiersmen.service.model.event.MessageEvent;
import me.ericjiang.frontiersmen.service.util.RuntimeTypeAdapterFactory;

import java.util.function.BiConsumer;

public class ServletModule extends com.google.inject.servlet.ServletModule {
    @Override
    protected void configureServlets() {
        // provide injector to EndpointConfigurator - https://stackoverflow.com/a/32053412/6497736
        requestStaticInjection(EndpointConfigurator.class);

        // configure GameDao
        bind(GameDao.class).to(GameDaoDynamoDB.class);

        // configure GameMaster's event processors
        final MapBinder<Class<? extends GameEvent>, BiConsumer<? extends GameEvent, Game>> eventProcessors
                = MapBinder.newMapBinder(
                        binder(),
                        new TypeLiteral<Class<? extends GameEvent>>() {},
                        new TypeLiteral<BiConsumer<? extends GameEvent, Game>>() {});
        eventProcessors.addBinding(MessageEvent.class).to(MessageEventProcessor.class);
    }

    @Provides
    public Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(
                        RuntimeTypeAdapterFactory.of(GameEvent.class, "eventType")
                                .registerSubtype(MessageEvent.class))
                .create();
    }
}
