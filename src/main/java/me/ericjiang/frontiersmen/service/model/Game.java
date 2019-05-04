package me.ericjiang.frontiersmen.service.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game {

    private final String gameId;

    private final List<PlayerMessage> playerMessages = new ArrayList<>();

}
