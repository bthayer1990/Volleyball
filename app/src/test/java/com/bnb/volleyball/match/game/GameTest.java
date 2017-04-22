package com.bnb.volleyball.match.game;

import com.bnb.volleyball.Position;
import com.bnb.volleyball.Possession;
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
    public void homePointScored_addsPointToHomeTeam_whenGameNotStarted() {
        final Game target = new Game();
        target.homePointScored();
        assertEquals(0, target.getHomeScore());
    }

    @Test
    public void homePointScored_addsPointToHomeTeam_whenGameStarted() {
        final Game target = new Game();
        target.startGame();
        target.homePointScored();
        assertEquals(1, target.getHomeScore());
    }

    @Test
    public void awayPointScored_addsPointToAwayTeam_whenGameNotStarted() {
        final Game target = new Game();
        target.awayPointScored();
        assertEquals(0, target.getAwayScore());
    }

    @Test
    public void awayPointScored_addsPointToAwayTeam_whenGameStarted() {
        final Game target = new Game();
        target.startGame();
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

    @Test
    public void setPossession_setsPossession_whenGameHasNotStarted() {
        final Game target = new Game();
        assertNotEquals(Possession.AWAY, target.getPossession());
        target.setPossession(Possession.AWAY);
        target.startGame();
        assertEquals(Possession.AWAY, target.getPossession());
    }

    @Test
    public void setPossession_doesNotSetPossesion_whenGameHasStarted() {
        final Game target = new Game();
        target.startGame();
        assertEquals(Possession.HOME, target.getPossession());
        target.setPossession(Possession.AWAY);
        assertEquals(Possession.HOME, target.getPossession());
    }

    @Test
    public void inProgress_returnsFalse_whenGameHasNotBeenStarted() {
        final Game target = new Game();
        assertFalse(target.inProgress());
    }

    @Test
    public void inProgress_returnsTrue_whenGameHasStarted() {
        final Game target = new Game();
        target.startGame();
        assertTrue(target.inProgress());
    }
}
