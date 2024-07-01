package com.example.hibernate_practice.service;

import com.example.hibernate_practice.model.Match;
import com.example.hibernate_practice.model.Player;

public class OngoingMatchesService {

    public Match getMatch(Player player1, Player player2) {
        Match match = new Match(player1, player2, null);
        return match;
    }


}
