package com.bnb.volleyball.match.game.state;

import com.bnb.volleyball.Possession;
import com.bnb.volleyball.player.Positions;

public class GameState {
    public GameState(int homeScore, int awayScore, Possession possession, Positions positions) {
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.possession = possession;
        this.positions = positions;
    }

    public static final GameState EMPTY = new GameState(0, 0, Possession.HOME, null);

    private final int homeScore, awayScore;
    private final Possession possession;
    private final Positions positions;

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public Possession getPossession() {
        return possession;
    }

    public Positions getPositions() {
        return positions;
    }
}
