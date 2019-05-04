package me.ericjiang.frontiersmen.service.model.event;

import lombok.Data;

@Data
public abstract class GameEvent {
    private long eventTime;
    private long eventNumber;
    private String gameId;
    private String playerId;
}
