package me.ericjiang.frontiersmen.service.configuration;

import javax.inject.Inject;
import javax.websocket.server.ServerEndpointConfig;

import com.google.inject.Injector;

public class EndpointConfigurator extends ServerEndpointConfig.Configurator {

    @Inject
    private static Injector injector;

    @Override
    public <T> T getEndpointInstance(Class<T> clazz) {
        return injector.getInstance(clazz);
    }
}