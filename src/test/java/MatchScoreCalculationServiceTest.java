import com.example.hibernate_practice.model.EPlayer;
import com.example.hibernate_practice.service.MatchScoreCalculationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MatchScoreCalculationServiceTest {
    private MatchScoreCalculationService service;
    private EPlayer player1;
    private EPlayer player2;

    @BeforeEach
    void setUp() {
        service = new MatchScoreCalculationService();
        player1 = EPlayer.FIRST_PLAYER;
        player2 = EPlayer.SECOND_PLAYER;
    }

    @Test
    void testGamesCalculation() {
        service.playerWins15Points(player1);
        service.playerWins15Points(player1);
        service.playerWins15Points(player1);
        service.playerWins15Points(player1);
        int player1Games = service.getMatchScore().getPlayerGames(player1);
        Assertions.assertEquals(1, player1Games);
    }

    @Test
    void testPointsCalculation() {
        service.playerWins15Points(player1);
        service.playerWins15Points(player1);
        service.playerWins15Points(player1);
        service.playerWins15Points(player2);
        service.playerWins15Points(player1);
        Assertions.assertEquals(0, service.getMatchScore().getPlayerPoints(player2));
    }

    @Test
    void testSetsCalculation() {
        service.playerWinsSet(player1);
        service.playerWinsSet(player1);
        Assertions.assertTrue(service.getMatchScore().isMatchFinished());
    }

    @Test
    void testTieBreakStarted() {
        for (int i = 0; i < 6; i++) {
            service.playerWinsGame(player1);
            service.playerWinsGame(player2);
        }
        Assertions.assertTrue(service.getMatchScore().isTieBreak());
    }

    @Test
    void testTieBreakEnded() {
        for (int i = 0; i < 6; i++) {
            service.playerWinsGame(player1);
            service.playerWinsGame(player2);

            for (int j = 0; i < 6; i++) {
                service.playerWinsTieBreakPoint(player1);
                service.playerWinsTieBreakPoint(player2);
            }

            service.playerWinsTieBreakPoint(player1);
            service.playerWinsTieBreakPoint(player1);
            Assertions.assertFalse(service.getMatchScore().isTieBreak());
        }
    }

    @Test
    void testTieBreakCalculation() {
        for (int i = 0; i < 6; i++) {
            service.playerWinsGame(player1);
            service.playerWinsGame(player2);
        }
        for (int i = 0; i < 6; i++) {
            service.playerWinsTieBreakPoint(player1);
            service.playerWinsTieBreakPoint(player2);
        }
        service.playerWinsTieBreakPoint(player2);
        service.playerWinsTieBreakPoint(player2);
        Assertions.assertEquals(1, service.getMatchScore().getPlayerSets(player2));
    }

    @Test
    void testPlayerWinsGameWithoutTieBreak() {
        for (int i = 0; i < 5; i++) {
            service.playerWinsGame(player1);
        }
        service.playerWins15Points(player1);
        service.playerWins15Points(player1);
        service.playerWins15Points(player1);
        service.playerWins15Points(player1);
        Assertions.assertEquals(1, service.getMatchScore().getPlayerSets(player1));
    }

    @Test
    void testPlayerWinsSetDirectly() {
        service.playerWinsSet(player2);
        service.playerWinsSet(player2);
        Assertions.assertTrue(service.getMatchScore().isMatchFinished());
        Assertions.assertEquals(player2, service.getMatchScore().getWinner());
    }

    @Test
    void testTieBreakWithMultiplePoints() {
        for (int i = 0; i < 6; i++) {
            service.playerWinsGame(player1);
            service.playerWinsGame(player2);
        }
        for (int i = 0; i < 7; i++) {
            service.playerWinsTieBreakPoint(player1);
            service.playerWinsTieBreakPoint(player2);
        }
        service.playerWinsTieBreakPoint(player1);
        service.playerWinsTieBreakPoint(player1);
        Assertions.assertEquals(1, service.getMatchScore().getPlayerSets(player1));
    }

    @Test
    void testPlayerWinsAfterLeadingInTieBreak() {
        for (int i = 0; i < 6; i++) {
            service.playerWinsGame(player1);
            service.playerWinsGame(player2);
        }
        for (int i = 0; i < 6; i++) {
            service.playerWinsTieBreakPoint(player1);
        }
        service.playerWinsTieBreakPoint(player1);
        service.playerWinsTieBreakPoint(player1);
        Assertions.assertEquals(1, service.getMatchScore().getPlayerSets(player1));
    }
}