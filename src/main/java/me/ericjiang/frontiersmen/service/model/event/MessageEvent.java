package me.ericjiang.frontiersmen.service.model.event;

import lombok.Data;

@Data
public class MessageEvent extends GameEvent {
    private String message;
}
