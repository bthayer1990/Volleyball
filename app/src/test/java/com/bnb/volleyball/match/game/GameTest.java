package com.bnb.volleyball.match.game;

import com.bnb.volleyball.Position;
import com.bnb.volleyball.player.Player;

import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class GameTest {
    @Test
    public void homeScore_startsAtZero() {
        final Game target = new Game();
        assertEquals(0, target.getHomeScore());
    }

    @Test
    public void awayScore_startsAtZero() {
        final Game target = new Game();
        assertEquals(0, target.getAwayScore());
    }

    @Test
    public void homePointScored_addsPointToHomeTeam() {
        final Game target = new Game();
        target.homePointScored();
        assertEquals(1, target.getHomeScore());
    }

    @Test
    public void awayPointScored_addsPointToAwayTeam() {
        final Game target = new Game();
        target.awayPointScored();
        assertEquals(1, target.getAwayScore());
    }

    @Test
    public void setPlayerPosition_setsPosition_whenGameHasNotStarted() {
        final Game target = new Game();
        final Player player = new Player();
        target.setPlayerPosition(Position.AWAY_FIFTH, player);
        final Map<Position, Player> playerMap = target.getPlayerMap();
        assertEquals(player, playerMap.get(Position.AWAY_FIFTH));
    }
}
