package com.bnb.volleyball.match.game;

import com.bnb.volleyball.Position;
import com.bnb.volleyball.player.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Game {
    private int homeScore = 0, awayScore = 0;

    private final Map<Position, Player> playerMap = new HashMap<>();

    private boolean started = false;

    public void startGame() { if(!started) { started = true; } }

    public void homePointScored() {
        ++homeScore;
    }

    public void awayPointScored() {
        ++awayScore;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setPlayerPosition(final Position position, final Player player) {
        if(!started) {
            playerMap.put(position, player);
        }
    }

    public Map<Position, Player> getPlayerMap() {
        return Collections.unmodifiableMap(playerMap);
    }
}
