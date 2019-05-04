package me.ericjiang.frontiersmen.service.endpoint;

import static org.junit.jupiter.api.Assertions.*;

import javax.websocket.DecodeException;
import javax.websocket.EncodeException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.gson.Gson;

import me.ericjiang.frontiersmen.service.configuration.ServletModule;
import me.ericjiang.frontiersmen.service.model.event.GameEvent;
import me.ericjiang.frontiersmen.service.model.event.MessageEvent;

class GameEventSerializerTest {

    private GameEventSerializer serializer;

    @BeforeEach
    void initializeUnit() {
        Gson gson = new ServletModule().provideGson();
        serializer = new GameEventSerializer(gson);
    }

    @Test
    void test() throws EncodeException, DecodeException {
        GameEvent event = new MessageEvent();
        String encoded = serializer.encode(event);
        GameEvent decoded = serializer.decode(encoded);
        assertEquals(event, decoded);
    }
}