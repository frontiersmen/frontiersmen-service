package me.ericjiang.frontiersmen.service.endpoint;

import com.google.gson.Gson;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import me.ericjiang.frontiersmen.service.model.GameEvent;

public class GameEventDecoder implements Decoder.Text<GameEvent> {

    private final Gson gson = new Gson();

    @Override
    public GameEvent decode(String s) throws DecodeException {
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
    }

    @Override
    public void destroy() {
    }
}
