package com.example.hibernate_practice.controllers;

import com.example.hibernate_practice.dao.PlayerDAO;
import com.example.hibernate_practice.model.EPlayer;
import com.example.hibernate_practice.model.Match;
import com.example.hibernate_practice.model.MatchScore;
import com.example.hibernate_practice.model.Player;
import com.example.hibernate_practice.service.FinishedMatchesPersistenceService;
import com.example.hibernate_practice.service.MatchScoreCalculationService;
import com.example.hibernate_practice.service.OngoingMatchesService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "match_score_controller", value = "/match-score/*")
public class MatchScoreController extends HttpServlet {
    MatchScoreCalculationService matchScoreCalculationService = new MatchScoreCalculationService();

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        Optional<Match> matchOpt = OngoingMatchesService.getMatch(uuid);
        if (matchOpt.isPresent()) {
            Match match = matchOpt.get();

            request.setAttribute("uuid", uuid);
            request.setAttribute("player1Name", match.getPlayer1().getName());
            request.setAttribute("player2Name", match.getPlayer2().getName());
            request.setAttribute("player1Points", 0);
            request.setAttribute("player2Points", 0);
            request.setAttribute("player1Games", 0);
            request.setAttribute("player2Games", 0);
            request.setAttribute("player1Sets", 0);
            request.setAttribute("player2Sets", 0);
            request.setAttribute("player1TieBreakPoints", 0);
            request.setAttribute("player2TieBreakPoints", 0);
            request.setAttribute("isTieBreak", match.getMatchScore().isTieBreak());
            request.getRequestDispatcher("/WEB-INF/MatchScore.jsp").forward(request, response);
        } else {
            request.setAttribute("errorCode", 500);
            request.setAttribute("errorMessage", "Не удалось найти матч по UUID.");
            request.getRequestDispatcher("/WEB-INF/Error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        Optional<Match> matchOpt = OngoingMatchesService.getMatch(uuid);
        if (matchOpt.isPresent()) {
            Match match = matchOpt.get();
            MatchScore matchScore = match.getMatchScore();
            this.matchScoreCalculationService.setMatchScore(matchScore);
            String player_winner_point = req.getParameter("player_winner_point");
            if (!matchScore.isTieBreak()) {
                if (player_winner_point.equals("FIRST_PLAYER")) {
                    this.matchScoreCalculationService.playerWins15Points(EPlayer.FIRST_PLAYER);
                    if (!matchScore.isTieBreak()) {
                        req.setAttribute("player1TieBreakPoints", 0);
                        req.setAttribute("player2TieBreakPoints", 0);
                    }
                } else if (player_winner_point.equals("SECOND_PLAYER")) {
                    this.matchScoreCalculationService.playerWins15Points(EPlayer.SECOND_PLAYER);
                    if (!matchScore.isTieBreak()) {
                        req.setAttribute("player1TieBreakPoints", 0);
                        req.setAttribute("player2TieBreakPoints", 0);
                    }
                }
            } else {
                if (player_winner_point.equals("FIRST_PLAYER")) {
                    this.matchScoreCalculationService.playerWinsTieBreakPoint(EPlayer.FIRST_PLAYER);
                    if (!matchScore.isTieBreak()) {
                        req.setAttribute("player1TieBreakPoints", 0);
                        req.setAttribute("player2TieBreakPoints", 0);
                    }
                } else if (player_winner_point.equals("SECOND_PLAYER")) {
                    this.matchScoreCalculationService.playerWinsTieBreakPoint(EPlayer.SECOND_PLAYER);
                    if (!matchScore.isTieBreak()) {
                        req.setAttribute("player1TieBreakPoints", 0);
                        req.setAttribute("player2TieBreakPoints", 0);
                    }
                }
            }

            match.setMatchScore(this.matchScoreCalculationService.getMatchScore());
            OngoingMatchesService.removeMatch(uuid);
            OngoingMatchesService.addMatchNotUniqueUUID(match, uuid);

            if (match.getMatchScore().isMatchFinished()) {
                Player player1 = match.getPlayer1();
                Player player2 = match.getPlayer2();
                PlayerDAO playerDAO = PlayerDAO.getInstance();
                Optional<Player> player1Opt = playerDAO.findPlayerByName(player1.getName());
                Optional<Player> player2Opt = playerDAO.findPlayerByName(player2.getName());
                if (player1Opt.isEmpty())
                    playerDAO.savePlayer(player1);
                if (player2Opt.isEmpty()) {
                    playerDAO.savePlayer(player2);
                }
                FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
                EPlayer winner = match.getMatchScore().getWinner();

                if (winner.ordinal() == 0)
                    match.setWinner(match.getPlayer1());
                 else if (winner.ordinal() == 1)
                    match.setWinner(match.getPlayer2());

                finishedMatchesPersistenceService.addMatch(match);

                OngoingMatchesService.removeMatch(uuid);
                req.setAttribute("player1Name", match.getPlayer1().getName());
                req.setAttribute("player2Name", match.getPlayer2().getName());
                if (winner == EPlayer.FIRST_PLAYER)
                    req.setAttribute("winnerName", match.getPlayer1().getName());
                else if (winner == EPlayer.SECOND_PLAYER)
                    req.setAttribute("winnerName", match.getPlayer2().getName());

                req.getRequestDispatcher("/WEB-INF/MatchResult.jsp").forward(req, resp);
            }

            req.setAttribute("uuid", uuid);
            req.setAttribute("player1Name", match.getPlayer1().getName());
            req.setAttribute("player2Name", match.getPlayer2().getName());
            req.setAttribute("player1Points", match.getMatchScore().getPlayerPoints(EPlayer.FIRST_PLAYER));
            req.setAttribute("player2Points", match.getMatchScore().getPlayerPoints(EPlayer.SECOND_PLAYER));
            req.setAttribute("player1Games", match.getMatchScore().getPlayerGames(EPlayer.FIRST_PLAYER));
            req.setAttribute("player2Games", match.getMatchScore().getPlayerGames(EPlayer.SECOND_PLAYER));
            req.setAttribute("player1TieBreakPoints", match.getMatchScore().getPlayerTieBreakPoints(EPlayer.FIRST_PLAYER));
            req.setAttribute("player2TieBreakPoints", match.getMatchScore().getPlayerTieBreakPoints(EPlayer.SECOND_PLAYER));
            req.setAttribute("isTieBreak", match.getMatchScore().isTieBreak());
            req.setAttribute("player1Sets", match.getMatchScore().getPlayerSets(EPlayer.FIRST_PLAYER));
            req.setAttribute("player2Sets", match.getMatchScore().getPlayerSets(EPlayer.SECOND_PLAYER));
            req.getRequestDispatcher("/WEB-INF/MatchScore.jsp").forward(req, resp);

        } else {
            req.setAttribute("errorCode", 500);
            req.setAttribute("errorMessage", "Не удалось найти матч по UUID.");
            req.getRequestDispatcher("/WEB-INF/Error.jsp").forward(req, resp);
        }
    }
}