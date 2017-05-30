package com.bnb.volleyball.match.game;

import com.bnb.volleyball.Position;
import com.bnb.volleyball.Possession;
import com.bnb.volleyball.match.game.state.GameState;
import com.bnb.volleyball.player.Player;
import com.bnb.volleyball.player.Positions;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Game {

    private Possession possession = Possession.HOME;
    private final Map<Position, Player> playerMap = new HashMap<>();
    private boolean started = false, ended = false;
    private final int WINNING_SCORE = 21;

    private final Stack<GameState> gameStates = new Stack<>();

    public boolean inProgress() {
        return (started == true && ended == false);
    }

    //games at lower levels are timed so there needs to be a mechanism to end the game
    public void endGame() { ended = true; }

    public void startGame() { if(!inProgress()) { started = true; } }

    public void homePointScored() {
        if(inProgress()) {
            final GameState current = current();
            gameStates.push(newGameState(current.getHomeScore() + 1, current.getAwayScore(),
                    possession == Possession.HOME ? possession : Possession.AWAY, null));
            if(gameIsWon(current.getHomeScore(), current.getAwayScore())){
                endGame();
            }
        }
    }

    public void awayPointScored() {
        if(inProgress()) {
            final GameState current = current();
            gameStates.push(newGameState(current.getHomeScore(), current.getAwayScore() + 1,
                    possession == Possession.AWAY ? possession : Possession.HOME, null));

            if(gameIsWon(current.getHomeScore(), current.getAwayScore())){
                endGame();
            }
        }
    }

    public int getHomeScore() {
        return current().getHomeScore();
    }

    public int getAwayScore() {
        return current().getAwayScore();
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

    private GameState newGameState(final int homeScore, final int awayScore,
                                   final Possession possession,
                                   final Positions positions) {
        return new GameState(homeScore, awayScore, possession, positions);
    }

    private GameState current() {
        return gameStates.empty() ? GameState.EMPTY : gameStates.peek();
    }
}
