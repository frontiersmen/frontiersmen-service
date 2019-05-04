package me.ericjiang.frontiersmen.service.model;

import lombok.Data;

@Data
public class PlayerMessage {

    private final String playerId;

    private final String message;

    private final long messageTime;

}
