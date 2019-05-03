package me.ericjiang.frontiersmen.service.gamemaster;

import lombok.extern.slf4j.Slf4j;
import me.ericjiang.frontiersmen.service.model.Game;
import me.ericjiang.frontiersmen.service.model.GameEvent;

import java.util.function.BiConsumer;

@Slf4j
public class MessageEventProcessor implements BiConsumer<GameEvent, Game> {

    private static final String MESSAGE_VARIABLE = "message";

    @Override
    public void accept(GameEvent gameEvent, Game game) {
        log.debug("Message sent from {}: '{}'", gameEvent.getPlayerId(), gameEvent.getEventDetails().get(MESSAGE_VARIABLE));
    }
}
