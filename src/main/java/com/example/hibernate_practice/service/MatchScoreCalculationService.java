package com.example.hibernate_practice.service;

import com.example.hibernate_practice.model.EPlayer;
import com.example.hibernate_practice.model.MatchScore;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MatchScoreCalculationService {
    private MatchScore matchScore;

    public MatchScoreCalculationService() {
        this.matchScore = new MatchScore();
    }

    public void playerWins15Points(EPlayer player) {
        int currentPoints = this.matchScore.getPlayerPoints(player);
        if (matchScore.isTieBreak()) {
            playerWinsTieBreakPoint(player);
            return;
        }
        if (currentPoints == 30) {
            this.matchScore.setPlayerPoints(player, 40);
        } else if (currentPoints == 40) {
            playerWinsGame(player);
        } else {
            this.matchScore.setPlayerPoints(player, currentPoints + 15);
        }
    }

    public void playerWinsGame(EPlayer player) {
        int opponentGames = 0;
        int playerGames = this.matchScore.getPlayerGames(player);
        if (player == EPlayer.FIRST_PLAYER) {
            opponentGames = this.matchScore.getPlayerGames(EPlayer.SECOND_PLAYER);
        } else if (player == EPlayer.SECOND_PLAYER) {
            opponentGames = this.matchScore.getPlayerGames(EPlayer.FIRST_PLAYER);
        }

        if (playerGames == 5 && opponentGames == 6 || playerGames == 6 && opponentGames == 5) {
            this.matchScore.setPlayerGames(EPlayer.FIRST_PLAYER, 6);
            this.matchScore.setPlayerGames(EPlayer.SECOND_PLAYER, 6);
            startTieBreak();
        } else if (playerGames == 6) {
            playerWinsSet(player);
        } else {
            this.matchScore.increasePlayerGames(player);
        }
        if (opponentGames <= 4 && playerGames >= 5) {
            playerWinsSet(player);
        }

        this.matchScore.clearPoints();
    }

    public void playerWinsSet(EPlayer player) {
        int playerSets = this.matchScore.getPlayerSets(player);
        if (playerSets == 1) {
            this.matchScore.setPlayerSets(player, 2);
            this.matchScore.setWinner(player);
            this.matchScore.setMatchFinished(true);
        } else {
            this.matchScore.increasePlayerSets(player);
        }
        this.matchScore.clearGames();
        this.matchScore.clearPoints();
    }

    public void playerWinsTieBreakPoint(EPlayer player) {
        this.matchScore.increaseTieBreakPoints(player);

        int pointsPlayer1 = this.matchScore.getPlayerTieBreakPoints(EPlayer.FIRST_PLAYER);
        int pointsPlayer2 = this.matchScore.getPlayerTieBreakPoints(EPlayer.SECOND_PLAYER);

        if (pointsPlayer1 >= 7 && pointsPlayer1 > pointsPlayer2 + 1) {
            playerWinsSet(EPlayer.FIRST_PLAYER);
            this.matchScore.tieBreakEnded();
            this.matchScore.increaseTieBreakPoints(EPlayer.SECOND_PLAYER);
            this.matchScore.clearTieBreakPoints();
        } else if (pointsPlayer2 >= 7 && pointsPlayer2 > pointsPlayer1 + 1) {
            playerWinsSet(EPlayer.SECOND_PLAYER);
            this.matchScore.tieBreakEnded();
            this.matchScore.clearTieBreakPoints();
        }
        if (!matchScore.isTieBreak()) {
            matchScore.clearTieBreakPoints();
        }
    }

    public void startTieBreak() {
        this.matchScore.setTieBreak(true);
    }
}