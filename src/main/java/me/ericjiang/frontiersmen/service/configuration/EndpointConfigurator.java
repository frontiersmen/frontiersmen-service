package me.ericjiang.frontiersmen.service.configuration;

import javax.inject.Inject;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;

import com.google.gson.Gson;
import com.google.inject.Injector;

public class EndpointConfigurator extends ServerEndpointConfig.Configurator {

    public static final String GSON_USER_PROPERTY = "gson";

    @Inject
    private static Injector injector;

    @Override
    public <T> T getEndpointInstance(Class<T> clazz) {
        return injector.getInstance(clazz);
    }

    /**
     * Make Gson available to endpoint's encoder and decoder.
     */
    @Override
    public void modifyHandshake(ServerEndpointConfig sec, HandshakeRequest request, HandshakeResponse response) {
        sec.getUserProperties().computeIfAbsent(GSON_USER_PROPERTY, (key) -> injector.getInstance(Gson.class));
    }
}