package me.ericjiang.frontiersmen.service;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint(value = "/game/{gameId}/player/{playerId}")
public class GameEndpoint {

    @OnOpen
    public void onOpen(
            Session session,
            @PathParam("gameId") String gameId,
            @PathParam("playerId") String playerId) {
        // Get current Game state and return to user
        // Subscribe session to updates
    }

    @OnMessage
    public void onMessage(
            Session session,
            String message,
            @PathParam("gameId") String gameId,
            @PathParam("playerId") String playerId) throws IOException {
        // Translate and forward GameEvents
        session.getBasicRemote().sendText(message);
    }

    @OnClose
    public void onClose(Session session) {
        // Unsubscribe session
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }
}
