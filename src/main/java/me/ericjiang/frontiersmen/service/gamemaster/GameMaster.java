package me.ericjiang.frontiersmen.service.gamemaster;

import lombok.extern.slf4j.Slf4j;
import me.ericjiang.frontiersmen.service.dao.GameDao;
import me.ericjiang.frontiersmen.service.model.Game;
import me.ericjiang.frontiersmen.service.model.event.GameEvent;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

import javax.inject.Inject;

/**
 * "A gamemaster (GM; also known as game master, game manager, game moderator or referee) is a person who acts as an
 * organizer, officiant for regarding rules, arbitrator, and moderator for a multiplayer role-playing game."
 *
 * "The role of a gamemaster in a traditional table-top role-playing game (pencil-and-paper role-playing game) is to
 * weave the other participants' player-character stories together, control the non-player aspects of the game, create
 * environments in which the players can interact, and solve any player disputes. The basic role of the gamemaster is
 * the same in almost all traditional role-playing games, although differing rule sets make the specific duties of the
 * gamemaster unique to that system."
 *
 * "The term gamemaster and the role associated with it could be found in the postal gaming hobby. In typical
 * play-by-mail games, players control armies or civilizations and mail their chosen actions to the GM. The GM then
 * mails the updated game state to all players on a regular basis."
 *
 * https://en.wikipedia.org/wiki/Gamemaster
 */
@Slf4j
public class GameMaster {

    private final GameDao gameDao;

    private final Map<Class<? extends GameEvent>, BiConsumer<? extends GameEvent, Game>> eventProcessors;

    @Inject
    public GameMaster(
            GameDao gameDao,
            Map<Class<? extends GameEvent>, BiConsumer<? extends GameEvent, Game>> eventProcessors) {
        this.gameDao = gameDao;
        this.eventProcessors = eventProcessors;
    }

    public <T extends GameEvent> void processEvent(T event) {
        log.info("Processing {}", event);
        // get game
        final Game game = gameDao.getGame(event.getGameId());
        // process event
        final Class<? extends GameEvent> eventType = event.getClass();
        Optional.ofNullable((BiConsumer<T, Game>) eventProcessors.get(eventType))
                .orElseThrow(() -> new UnsupportedOperationException(
                            String.format("Unsupported event type '%s'", eventType)))
                .accept(event, game);

        // update game
        gameDao.updateGame(game);
        log.debug("{}", game);
    }
}
