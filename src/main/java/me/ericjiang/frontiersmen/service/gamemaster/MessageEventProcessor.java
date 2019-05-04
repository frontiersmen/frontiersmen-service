package me.ericjiang.frontiersmen.service.gamemaster;

import lombok.extern.slf4j.Slf4j;
import me.ericjiang.frontiersmen.service.model.Game;
import me.ericjiang.frontiersmen.service.model.PlayerMessage;
import me.ericjiang.frontiersmen.service.model.event.MessageEvent;

import java.util.function.BiConsumer;

@Slf4j
public class MessageEventProcessor implements BiConsumer<MessageEvent, Game> {

    @Override
    public void accept(MessageEvent messageEvent, Game game) {
        final PlayerMessage message = new PlayerMessage(
                messageEvent.getPlayerId(),
                messageEvent.getMessage(),
                System.currentTimeMillis());
        game.getPlayerMessages().add(message);
        log.debug("Message sent from {}: '{}'", messageEvent.getPlayerId(), messageEvent.getMessage());
    }
}
