package me.ericjiang.frontiersmen.service.endpoint;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import me.ericjiang.frontiersmen.service.configuration.EndpointConfigurator;
import me.ericjiang.frontiersmen.service.model.event.GameEvent;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE, onConstructor=@__({@VisibleForTesting}))
public class GameEventSerializer implements Encoder.Text<GameEvent>, Decoder.Text<GameEvent> {

    private Gson gson;

    @Override
    public String encode(GameEvent event) throws EncodeException {
        Preconditions.checkNotNull(gson, "Gson was not initialized.");
        try {
            return gson.toJson(event, GameEvent.class);
        } catch (RuntimeException e) {
            throw new EncodeException(event, "Encoding failed.", e);
        }
    }

    @Override
    public GameEvent decode(String s) throws DecodeException {
        Preconditions.checkNotNull(gson, "Gson was not initialized.");
        try {
            return gson.fromJson(s, GameEvent.class);
        } catch (RuntimeException e) {
            throw new DecodeException(s, "Decoding failed.", e);
        }
    }

    @Override
    public boolean willDecode(String s) {
        return true;
    }

    @Override
    public void init(EndpointConfig config) {
        gson = (Gson) config.getUserProperties().get(EndpointConfigurator.GSON_USER_PROPERTY);
    }

    @Override
    public void destroy() {
    }
}
