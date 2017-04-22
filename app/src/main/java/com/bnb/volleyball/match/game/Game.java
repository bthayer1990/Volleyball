package com.bnb.volleyball.match.game;

import com.bnb.volleyball.Position;
import com.bnb.volleyball.Possession;
import com.bnb.volleyball.player.Player;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Game {

    private int homeScore = 0, awayScore = 0;
    private Possession possession = Possession.HOME;
    private final Map<Position, Player> playerMap = new HashMap<>();
    private boolean started = false, ended = false;

    /* A game is in progress if it has started and no team has won,
        winning does not occur until 21 points but a game must be won by
        two ie 21-19, 22-20, 24-22
     */
    public boolean inProgress() { return started; }

    //games at lower levels are timed so there needs to be a mechanism to end the game
    public void endGame() { started = false; ended = true; }

    public void startGame() { if(!inProgress()) { started = true; } }

    public void homePointScored() {
        if(inProgress()) { ++homeScore; }
    }

    public void awayPointScored() {
        if(inProgress()) { ++awayScore; }
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setPlayerPosition(final Position position, final Player player) {
        if(!inProgress()) {
            playerMap.put(position, player);
        }
    }

    public Possession getPossession() { return possession; }
    public void setPossession(final Possession possession) { if(!inProgress()) { this.possession = possession; } }

    public Map<Position, Player> getPlayerMap() {
        return Collections.unmodifiableMap(playerMap);
    }
}
