package me.ericjiang.frontiersmen.service.model;

import lombok.Data;

import java.util.Map;

@Data
public class GameEvent {
    private final String eventType;
    private final long eventTime;
    private final long eventNumber;
    private final Map<String, Object> eventDetails;
    private String gameId;
    private String playerId;
}
