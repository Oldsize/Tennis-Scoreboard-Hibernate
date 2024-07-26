package com.example.hibernate_practice.service;

import com.example.hibernate_practice.dao.PlayerDAO;
import com.example.hibernate_practice.model.Match;
import com.example.hibernate_practice.model.Player;

import java.util.Optional;

public class NewMatchService {
    public Match createMatch(String player1Name, String player2Name) {
        PlayerDAO playerDAO = PlayerDAO.getInstance();
        Optional<Player> optPlayer1 = playerDAO.findByName(player1Name);
        Optional<Player> optPlayer2 = playerDAO.findByName(player2Name);

        Player player1;
        Player player2;

        if (optPlayer1.isEmpty()) {
            player1 = new Player(player1Name);
            playerDAO.save(player1);
        } else {
            player1 = optPlayer1.get();
        }

        if (optPlayer2.isEmpty()) {
            player2 = new Player(player2Name);
            playerDAO.save(player2);
        } else {
            player2 = optPlayer2.get();
        }
        return new Match(player1, player2);
    }
}
