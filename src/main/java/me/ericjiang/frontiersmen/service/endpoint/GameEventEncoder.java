package me.ericjiang.frontiersmen.service.endpoint;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import me.ericjiang.frontiersmen.service.model.GameEvent;

public class GameEventEncoder implements Encoder.Text<GameEvent> {

    private final Gson gson = new Gson();

    @Override
    public String encode(GameEvent event) throws EncodeException {
        try {
            return gson.toJson(event);
        } catch (RuntimeException e) {
            throw new EncodeException(event, "Encoding failed.", e);
        }
    }

    @Override
    public void init(EndpointConfig config) {
    }

    @Override
    public void destroy() {
    }
}
