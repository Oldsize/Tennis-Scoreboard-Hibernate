package com.example.hibernate_practice.model;

import lombok.Getter;
import lombok.Setter;

public class MatchScore {
    @Getter
    @Setter
    private EPlayer winner;

    @Getter
    @Setter
    private boolean tieBreak;

    @Getter
    @Setter
    private boolean matchFinished;

    private final int[] points;
    private final int[] games;
    private final int[] sets;
    private final int[] tieBreakPoints;

    public MatchScore() {
        this.points = new int[]{0, 0};
        this.games = new int[]{0, 0};
        this.sets = new int[]{0, 0};
        this.tieBreakPoints = new int[]{0, 0};
        this.tieBreak = false;
        this.matchFinished = false;
        this.winner = null;
    }

    public int getPlayerPoints(EPlayer player) {
        return this.points[player.ordinal()];
    }

    public void setPlayerPoints(EPlayer player, int points) {
        this.points[player.ordinal()] = points;
    }

    public int getPlayerGames(EPlayer player) {
        return this.games[player.ordinal()];
    }

    public void setPlayerGames(EPlayer player, int games) {
        this.games[player.ordinal()] = games;
    }

    public void increasePlayerGames(EPlayer player) {
        this.games[player.ordinal()]++;
    }

    public int getPlayerSets(EPlayer player) {
        return this.sets[player.ordinal()];
    }

    public void setPlayerSets(EPlayer player, int sets) {
        this.sets[player.ordinal()] = sets;
    }

    public void increasePlayerSets(EPlayer player) {
        this.sets[player.ordinal()]++;
    }

    public void clearTieBreakPoints() {
        tieBreakPoints[0] = 0;
        tieBreakPoints[1] = 0;
    }

    public void clearPoints() {
        points[0] = 0;
        points[1] = 0;
    }

    public int getPlayerTieBreakPoints(EPlayer player) {
        return tieBreakPoints[player.ordinal()];
    }

    public void increaseTieBreakPoints(EPlayer player) {
        tieBreakPoints[player.ordinal()]++;
    }

    public void tieBreakEnded() {
        tieBreak = false;
    }

    public void clearGames() {
        games[0] = 0;
        games[1] = 0;
    }
}