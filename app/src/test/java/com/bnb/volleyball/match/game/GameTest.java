package com.bnb.volleyball.match.game;

import com.bnb.volleyball.Position;
import com.bnb.volleyball.Possession;
import com.bnb.volleyball.player.Player;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.*;

public class GameTest {
    Game target;

    @Before
    public void setUp(){
        target = new Game();
    }

    @Test
    public void homeScore_startsAtZero() {
        assertEquals(0, target.getHomeScore());
    }

    @Test
    public void awayScore_startsAtZero() {
        assertEquals(0, target.getAwayScore());
    }

    @Test
    public void homePointScored_doesNotAddPointToHomeTeam_whenGameNotStarted() {
        target.homePointScored();
        assertEquals(0, target.getHomeScore());
    }

    @Test
    public void homePointScored_addsPointToHomeTeam_whenGameStarted() {
        target.startGame();
        target.homePointScored();
        assertEquals(1, target.getHomeScore());
    }

    @Test
    public void homePointScored_setsGameToEnded_whenScoreIs21OrHigherWith2PointDifference(){
        target.startGame();
        ScoreManyAwayPoints(21);
        ScoreManyHomePoints(23);
        assertFalse(target.inProgress());
    }

    @Test
    public void homePointScore_doesNotSetGameToEnded_whenScoreIs21OrHigherWithLessThan2PointDifference(){
        target.startGame();
        ScoreManyAwayPoints(20);
        ScoreManyHomePoints(21);
        assertTrue(target.inProgress());
    }

    @Test
    public void awayPointScored_doesNotAddPointToHomeTeam_whenGameNotStarted() {
        target.awayPointScored();
        assertEquals(0, target.getAwayScore());
    }

    @Test
    public void awayPointScored_addsPointToAwayTeam_whenGameStarted() {
        target.startGame();
        target.awayPointScored();
        assertEquals(1, target.getAwayScore());
    }

    @Test
    public void awayPointScored_doesNotSetGameToEnded_WhenScoreIsLessThan21(){
        target.startGame();
        ScoreManyAwayPoints(20);
        assertTrue(target.inProgress());
    }

    @Test
    public void awayPointScored_setsGameToEnded_whenScoreIs21OrHigherWith2PointDifference(){
        target.startGame();
        ScoreManyHomePoints(21);
        ScoreManyAwayPoints(23);
        assertFalse(target.inProgress());
    }

    @Test
    public void awayPointScore_doesNotSetGameToEnded_whenScoreIs21OrHigherWithLessThan2PointDifference(){
        target.startGame();
        ScoreManyHomePoints(20);
        ScoreManyAwayPoints(21);
        assertTrue(target.inProgress());
    }

    @Test
    public void setPlayerPosition_setsPosition_whenGameHasNotStarted() {
        final Player player = new Player();
        target.setPlayerPosition(Position.AWAY_FIFTH, player);
        final Map<Position, Player> playerMap = target.getPlayerMap();
        assertEquals(player, playerMap.get(Position.AWAY_FIFTH));
    }

    @Test
    public void setPossession_setsPossession_whenGameHasNotStarted() {
        assertNotEquals(Possession.AWAY, target.getPossession());
        target.setPossession(Possession.AWAY);
        target.startGame();
        assertEquals(Possession.AWAY, target.getPossession());
    }

    @Test
    public void setPossession_doesNotSetPossesion_whenGameHasStarted() {
        target.startGame();
        assertEquals(Possession.HOME, target.getPossession());
        target.setPossession(Possession.AWAY);
        assertEquals(Possession.HOME, target.getPossession());
    }

    @Test
    public void inProgress_returnsFalse_whenGameHasNotBeenStarted() {
        assertFalse(target.inProgress());
    }

    @Test
    public void inProgress_returnsTrue_whenGameHasStarted() {
        target.startGame();
        assertTrue(target.inProgress());
    }

    private void ScoreManyHomePoints(int numberOfPoints){
        for(int i = 0; i < numberOfPoints; i++){
            target.homePointScored();
        }
    }

    private void ScoreManyAwayPoints(int numberOfPoints){
        for(int i = 0; i < numberOfPoints; i++){
            target.awayPointScored();
        }
    }
}
