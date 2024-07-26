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

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uuid = req.getParameter("uuid");
        Optional<Match> matchOpt = OngoingMatchesService.getMatch(uuid);

        if (matchOpt.isPresent()) {
            Match match = matchOpt.get();
            MatchScore matchScore = match.getMatchScore();
            req.setAttribute("uuid", uuid);
            req.setAttribute("matchScore", matchScore);
            req.setAttribute("match", match);

            req.getRequestDispatcher("/WEB-INF/MatchScore.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorCode", 500);
            req.setAttribute("errorMessage", "Не удалось найти матч по UUID.");
            req.getRequestDispatcher("/WEB-INF/Error.jsp").forward(req, resp);
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
            if (player_winner_point.equals("FIRST_PLAYER")) {
                this.matchScoreCalculationService.playerWins15Points(EPlayer.FIRST_PLAYER);
            } else if (player_winner_point.equals("SECOND_PLAYER")) {
                this.matchScoreCalculationService.playerWins15Points(EPlayer.SECOND_PLAYER);

            }
            match.setMatchScore(this.matchScoreCalculationService.getMatchScore());
            OngoingMatchesService.removeMatch(uuid);
            OngoingMatchesService.addMatchNotUniqueUUID(match, uuid);

            if (match.getMatchScore().isMatchFinished()) {
                Player player1 = match.getPlayer1();
                Player player2 = match.getPlayer2();
                PlayerDAO playerDAO = PlayerDAO.getInstance();
                Optional<Player> player1Opt = playerDAO.findByName(player1.getName());
                Optional<Player> player2Opt = playerDAO.findByName(player2.getName());
                if (player1Opt.isEmpty())
                    playerDAO.save(player1);
                if (player2Opt.isEmpty())
                    playerDAO.save(player2);

                FinishedMatchesPersistenceService finishedMatchesPersistenceService = new FinishedMatchesPersistenceService();
                EPlayer winner = match.getMatchScore().getWinner();
                if (winner.ordinal() == 0)
                    match.setWinner(match.getPlayer1());
                else if (winner.ordinal() == 1)
                    match.setWinner(match.getPlayer2());
                finishedMatchesPersistenceService.addMatch(match);
                OngoingMatchesService.removeMatch(uuid);
                req.setAttribute("match", match);
                req.getRequestDispatcher("/WEB-INF/MatchResult.jsp").forward(req, resp);
            }
            req.setAttribute("uuid", uuid);
            req.setAttribute("match", match);
            req.setAttribute("matchScore", match.getMatchScore());
            req.getRequestDispatcher("/WEB-INF/MatchScore.jsp").forward(req, resp);
        } else {
            req.setAttribute("errorCode", 404);
            req.setAttribute("errorMessage", "Не удалось найти матч по UUID.");
            req.getRequestDispatcher("/WEB-INF/Error.jsp").forward(req, resp);
        }
    }
}