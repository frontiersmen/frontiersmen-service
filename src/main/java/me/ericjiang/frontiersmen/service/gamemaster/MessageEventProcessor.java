package me.ericjiang.frontiersmen.service.gamemaster;

import lombok.extern.slf4j.Slf4j;
import me.ericjiang.frontiersmen.service.model.Game;
import me.ericjiang.frontiersmen.service.model.GameEvent;
import me.ericjiang.frontiersmen.service.model.PlayerMessage;

import java.util.function.BiConsumer;

@Slf4j
public class MessageEventProcessor implements BiConsumer<GameEvent, Game> {

    private static final String MESSAGE_VARIABLE = "message";

    @Override
    public void accept(GameEvent gameEvent, Game game) {
        final PlayerMessage message = new PlayerMessage(
                gameEvent.getPlayerId(),
                gameEvent.getEventDetails().get(MESSAGE_VARIABLE).toString(),
                System.currentTimeMillis());
        game.getPlayerMessages().add(message);
        log.debug("Message sent from {}: '{}'", gameEvent.getPlayerId(), gameEvent.getEventDetails().get(MESSAGE_VARIABLE));
    }
}
