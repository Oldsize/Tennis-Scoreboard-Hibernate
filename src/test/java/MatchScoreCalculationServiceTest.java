import com.example.hibernate_practice.model.EPlayer;
import com.example.hibernate_practice.service.MatchScoreCalculationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MatchScoreCalculationServiceTest {
    @Test
    void testGamesCalculation() {
        MatchScoreCalculationService service = new MatchScoreCalculationService();
        EPlayer player1 = EPlayer.FIRST_PLAYER;
        EPlayer player2 = EPlayer.SECOND_PLAYER;
        service.playerWins15Points(player1);
        service.playerWins15Points(player1);
        service.playerWins15Points(player1);
        service.playerWins15Points(player1);
        int player1Games = service.getMatchScore().getPlayerGames(player1);
        Assertions.assertEquals(1, player1Games);
    }

    @Test
    void testPointsCalculation() {
        MatchScoreCalculationService service = new MatchScoreCalculationService();
        EPlayer player1 = EPlayer.FIRST_PLAYER;
        EPlayer player2 = EPlayer.SECOND_PLAYER;
        service.playerWins15Points(player1);
        service.playerWins15Points(player1);
        service.playerWins15Points(player1);
        service.playerWins15Points(player2);
        service.playerWins15Points(player1);
        Assertions.assertEquals(0, service.getMatchScore().getPlayerPoints(player2));
    }

    @Test
    void testSetsCalculation() {
        MatchScoreCalculationService service = new MatchScoreCalculationService();
        EPlayer player1 = EPlayer.FIRST_PLAYER;
        EPlayer player2 = EPlayer.SECOND_PLAYER;
        service.playerWinsSet(player1);
        service.playerWinsSet(player1);
        Assertions.assertTrue(service.getMatchScore().isMatchFinished());
    }

    @Test
    void testTieBreakStarted() {
        MatchScoreCalculationService service = new MatchScoreCalculationService();
        EPlayer player1 = EPlayer.FIRST_PLAYER;
        EPlayer player2 = EPlayer.SECOND_PLAYER;
        service.playerWinsGame(player1);
        service.playerWinsGame(player1);
        service.playerWinsGame(player1);
        service.playerWinsGame(player1);
        service.playerWinsGame(player1);
        service.playerWinsGame(player1);
        service.playerWinsGame(player2);
        service.playerWinsGame(player2);
        service.playerWinsGame(player2);
        service.playerWinsGame(player2);
        service.playerWinsGame(player2);
        service.playerWinsGame(player2);
        Assertions.assertTrue(service.getMatchScore().isTieBreak());
    }

    @Test
    void testTieBreakEnded() {
        MatchScoreCalculationService service = new MatchScoreCalculationService();
        EPlayer player1 = EPlayer.FIRST_PLAYER;
        EPlayer player2 = EPlayer.SECOND_PLAYER;
        service.playerWinsGame(player1);
        service.playerWinsGame(player1);
        service.playerWinsGame(player1);
        service.playerWinsGame(player1);
        service.playerWinsGame(player1);
        service.playerWinsGame(player1);
        service.playerWinsGame(player2);
        service.playerWinsGame(player2);
        service.playerWinsGame(player2);
        service.playerWinsGame(player2);
        service.playerWinsGame(player2);
        service.playerWinsGame(player2);
        service.playerWinsTieBreakPoint(player1);
        service.playerWinsTieBreakPoint(player1);
        Assertions.assertFalse(service.getMatchScore().isTieBreak());
    }

    @Test
    void testTieBreakCalculation() {
        MatchScoreCalculationService service = new MatchScoreCalculationService();
        EPlayer player1 = EPlayer.FIRST_PLAYER;
        EPlayer player2 = EPlayer.SECOND_PLAYER;
        service.playerWinsGame(player1);
        service.playerWinsGame(player1);
        service.playerWinsGame(player1);
        service.playerWinsGame(player1);
        service.playerWinsGame(player1);
        service.playerWinsGame(player1);
        service.playerWinsGame(player2);
        service.playerWinsGame(player2);
        service.playerWinsGame(player2);
        service.playerWinsGame(player2);
        service.playerWinsGame(player2);
        service.playerWinsGame(player2);
        service.playerWinsTieBreakPoint(player1);
        service.playerWinsTieBreakPoint(player2);
        service.playerWinsTieBreakPoint(player1);
        service.playerWinsTieBreakPoint(player2);
        service.playerWinsTieBreakPoint(player1);
        service.playerWinsTieBreakPoint(player2);
        service.playerWinsTieBreakPoint(player1);
        service.playerWinsTieBreakPoint(player2);
        service.playerWinsTieBreakPoint(player1);
        service.playerWinsTieBreakPoint(player2);
        service.playerWinsTieBreakPoint(player1);
        service.playerWinsTieBreakPoint(player2);
        service.playerWinsTieBreakPoint(player1);
        service.playerWinsTieBreakPoint(player2);
        service.playerWinsTieBreakPoint(player1);
        service.playerWinsTieBreakPoint(player2);
        service.playerWinsTieBreakPoint(player1);
        service.playerWinsTieBreakPoint(player2);
        service.playerWinsTieBreakPoint(player1);
        service.playerWinsTieBreakPoint(player2);
        service.playerWinsTieBreakPoint(player2);
        service.playerWinsTieBreakPoint(player2);
        Assertions.assertEquals(1, service.getMatchScore().getPlayerSets(player2));
    }

    @Test
    public void testSetsCalculation2() {
        MatchScoreCalculationService service = new MatchScoreCalculationService();
        EPlayer player1 = EPlayer.FIRST_PLAYER;
        EPlayer player2 = EPlayer.SECOND_PLAYER;
        // player 2 1 set
        service.playerWinsSet(player2);
        // player 2 1 game
        service.playerWins15Points(player1);
        service.playerWins15Points(player1);
        service.playerWins15Points(player1);
        service.playerWins15Points(player1);
        System.out.println(service.getMatchScore().getPlayerSets(player2));
        System.out.println(service.getMatchScore().getPlayerSets(player1));


    }
}
