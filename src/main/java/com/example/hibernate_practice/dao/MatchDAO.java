package com.example.hibernate_practice.dao;

import com.example.hibernate_practice.model.Match;
import com.example.hibernate_practice.utils.ConnectionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;


public class MatchDAO {

    ConnectionManager connectionManager = ConnectionManager.getInstance();

    private static final MatchDAO INSTANCE = new MatchDAO();

    SessionFactory factory = connectionManager.getSessionFactory();

    Session session = null;

    public static MatchDAO getInstance() {
        return INSTANCE;
    }

    private MatchDAO() {
    }


    public Match createMatch(Match match) {
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();

            session.save(match);

            session.getTransaction().commit();
        } finally {
            session.close();
            factory.close();
        }
        return match;
    }
}