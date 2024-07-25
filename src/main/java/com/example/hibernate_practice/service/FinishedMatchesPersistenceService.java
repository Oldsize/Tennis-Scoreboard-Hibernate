package com.example.hibernate_practice.service;

import com.example.hibernate_practice.dao.MatchDAO;
import com.example.hibernate_practice.model.Match;


public class FinishedMatchesPersistenceService {
    MatchDAO matchDAO;

    public FinishedMatchesPersistenceService() {
        matchDAO = MatchDAO.getInstance();
    }

    public void addMatch(Match match) {
        matchDAO.save(match);
    }
}
