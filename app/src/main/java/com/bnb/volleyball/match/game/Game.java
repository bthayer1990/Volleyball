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
    private final int WINNING_SCORE = 21;

    public boolean inProgress() {
        return (started == true && ended == false);
    }

    //games at lower levels are timed so there needs to be a mechanism to end the game
    public void endGame() { ended = true; }

    public void startGame() { if(!inProgress()) { started = true; } }

    public void homePointScored() {
        if(inProgress()) { ++homeScore; }

        if(gameIsWon(homeScore, awayScore)){
            endGame();
        }
    }

    public void awayPointScored() {
        if(inProgress()) { ++awayScore; }

        if(gameIsWon(awayScore, homeScore)){
            endGame();
        }
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

    private boolean gameIsWon(int thisScore, int otherScore){
        return thisScore >= WINNING_SCORE && pointDifferenceIsTwoOrMore(thisScore, otherScore);
    }

    private boolean pointDifferenceIsTwoOrMore(int thisScore, int otherScore){
        return (thisScore - otherScore) >= 2;
    }
}
