package me.ericjiang.frontiersmen.service;

import lombok.extern.slf4j.Slf4j;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@Slf4j
@ServerEndpoint(
        value = "/game/{gameId}/player/{playerId}",
        encoders = GameEventEncoder.class,
        decoders = GameEventDecoder.class)
public class GameEndpoint {

    @OnOpen
    public void onOpen(
            Session session,
            @PathParam("gameId") String gameId,
            @PathParam("playerId") String playerId) {
        // Get current Game state and return to user
        // Subscribe session to updates
        log.info("Player {} connected to game {}", playerId, gameId);
    }

    @OnMessage
    public void onMessage(
            Session session,
            GameEvent event,
            @PathParam("gameId") String gameId,
            @PathParam("playerId") String playerId) throws IOException, EncodeException {
        // Translate and forward GameEvents
        session.getBasicRemote().sendObject(event);
        log.debug("Received message from player {} to game {}: '{}'", playerId, gameId, event.toString());
    }

    @OnClose
    public void onClose(
            Session session,
            @PathParam("gameId") String gameId,
            @PathParam("playerId") String playerId) {
        // Unsubscribe session
        log.info("Player {} disconnected from game {}", playerId, gameId);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
        log.error("{}", throwable);
    }
}
