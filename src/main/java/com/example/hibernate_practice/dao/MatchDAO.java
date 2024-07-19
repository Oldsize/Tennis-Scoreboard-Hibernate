package com.example.hibernate_practice.dao;

import com.example.hibernate_practice.model.Match;
import com.example.hibernate_practice.utils.ConnectionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MatchDAO {
    private static final MatchDAO INSTANCE = new MatchDAO();

    private MatchDAO() {
    }

    public static MatchDAO getInstance() {
        return INSTANCE;
    }

    ConnectionManager connectionManager = ConnectionManager.getInstance();

    public Optional<Match> isNextPage(int currentPage) {
        SessionFactory factory = connectionManager.getSessionFactory();
        Session session = null;
        Optional<Match> matchOpt = Optional.empty();
        try {
            session = factory.getCurrentSession();
            int idMatch = currentPage * 10 + 1;
            session.beginTransaction();
            String hql = "FROM Match WHERE id = :id";
            matchOpt = session.createQuery(hql).setParameter("id", idMatch).uniqueResultOptional();
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return matchOpt;
    }

    public void saveMatch(Match match) {
        SessionFactory factory = connectionManager.getSessionFactory();
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.saveOrUpdate(match);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<Match> findMatchesByParticipantPlayer(String participantPlayer, int pageNumber) {
        int pageSize = 10;
        SessionFactory factory = connectionManager.getSessionFactory();
        Session session = null;
        List<Match> findedMatches = new ArrayList<>();
        String hql = "FROM Match WHERE player1 = :name OR player2 = :name";
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Query<Match> query = session.createQuery(hql, Match.class);
            query.setParameter("name", participantPlayer);
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);
            findedMatches = query.list();
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return findedMatches;
    }

    public boolean checkIfMatchesExists() {
        SessionFactory factory = connectionManager.getSessionFactory();
        Session session = null;
        Optional<Match> matchesExists = Optional.empty();
        String hql = "FROM Match where id = 1";
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            matchesExists = session.createQuery(hql).uniqueResultOptional();
            if (matchesExists.isEmpty())
                return false;
             else if (matchesExists.isPresent())
                return true;
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return false;
    }

    public List<Match> getMatchesByPage(int pageNumber) {
        int pageSize = 10;
        SessionFactory factory = connectionManager.getSessionFactory();
        Session session = null;
        List<Match> matches = new ArrayList<Match>();
        String hql = "FROM Match";
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            Query<Match> query = session.createQuery(hql, Match.class);
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);
            matches = query.list();
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return matches;
    }

    public List<Match> getAllMatches() {
        SessionFactory factory = connectionManager.getSessionFactory();
        Session session = null;
        List<Match> matches = new ArrayList<Match>();
        String hql = "FROM Match";
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            matches = session.createQuery(hql).list();
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return matches;
    }
}