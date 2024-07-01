package com.example.hibernate_practice.service;


import com.example.hibernate_practice.model.EPlayer;
import com.example.hibernate_practice.model.MatchScore;

public class MatchScoreCalculationService {
    MatchScore matchScore;

    public MatchScore getMatchScore() {
        return matchScore;
    }

    public MatchScoreCalculationService() {
        this.matchScore = new MatchScore();
        matchScore.clearTieBreakPoints();
        matchScore.clearPoints();
        matchScore.clearSets();
    }

    public void playerWins15Points(EPlayer player) {
        if (matchScore.getPlayerPoints(player) == 30)
            matchScore.setPlayerPoints(player, 40);

         else if (matchScore.getPlayerPoints(player) == 40) {
            matchScore.setPlayerGames(player, 1);
            matchScore.setPlayerPoints(player, 0);

            if (player.ordinal() == 0)
                matchScore.setPlayerPoints(EPlayer.SECOND_PLAYER, 0);
             else if (player.ordinal() == 1)
                matchScore.setPlayerPoints(EPlayer.FIRST_PLAYER, 0);

        } else
            matchScore.setPlayerPoints(player, matchScore.getPlayerPoints(player) + 15);

    }

    public void playerWinsGame(EPlayer player) {
        if (matchScore.getPlayerGames(EPlayer.FIRST_PLAYER) == 6
                || matchScore.getPlayerGames(EPlayer.SECOND_PLAYER) == 6) {
            startTieBreak();
        }

        if (matchScore.getPlayerGames(player) == 6) {
            matchScore.setPlayerGames(player, 0);
            playerWinsSet(player);
            if (player.ordinal() == 0)
                matchScore.setPlayerPoints(EPlayer.SECOND_PLAYER, 0);
            else if (player.ordinal() == 1)
                matchScore.setPlayerPoints(EPlayer.FIRST_PLAYER, 0);

        } else
            matchScore.increasePlayerGames(player);
    }

    public void playerWinsSet(EPlayer player) {
        if (matchScore.getPlayerSets(player) == 1) {
            matchScore.setPlayerSets(player, 2);
            matchScore.increasePlayerSets(player);
        } else
            matchScore.increasePlayerSets(player);
    }

    public MatchScore playerWinsMatch(EPlayer player) {
        matchScore.setWinner(player);
        matchScore.setMatchFinished(true);
        return matchScore;
    }

    public void playerWinsTieBreakPoint(EPlayer player) {


    }


    public void startTieBreak() {
        matchScore.setTieBreak(true);


    }


    // до 40 поинтов, до 6 (7) геймов, до двух сетов
    // если выиграл один гейм, то все поинты у противника обнуляются
}
