package com.example.hibernate_practice.dao;

import com.example.hibernate_practice.model.Match;
import com.example.hibernate_practice.model.Player;
import com.example.hibernate_practice.utils.ConnectionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

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
        Optional<Match> matchOpt;
        try (Session session = factory.getCurrentSession()) {
            int idMatch = currentPage * 10 + 1;
            session.beginTransaction();
            String hql = "FROM Match WHERE id = :id";
            matchOpt = session.createQuery(hql).setParameter("id", idMatch).uniqueResultOptional();
            session.getTransaction().commit();
        }
        return matchOpt;
    }

    public void save(Match match) {
        SessionFactory factory = connectionManager.getSessionFactory();
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            session.saveOrUpdate(match);
            session.getTransaction().commit();
        }
    }

    public List<Match> findByParticipantPlayer(String participantPlayer, int pageNumber) {
        int pageSize = 10;
        SessionFactory factory = connectionManager.getSessionFactory();
        PlayerDAO playerDAO = PlayerDAO.getInstance();
        List<Match> findedMatches;
        String hql = "FROM Match WHERE player1 = :name OR player2 = :name";
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            Player participantPlayerModel = new Player();
            if (!participantPlayer.isEmpty()) {
                participantPlayerModel = playerDAO.findByName(participantPlayer).get();
            }
            Query<Match> query = session.createQuery(hql, Match.class);
            query.setParameter("name", participantPlayerModel);
            query.setFirstResult((pageNumber - 1) * pageSize);
            query.setMaxResults(pageSize);
            findedMatches = query.list();
            session.getTransaction().commit();
        }
        return findedMatches;
    }

    public boolean checkIfMatchesExists() {
        SessionFactory factory = connectionManager.getSessionFactory();
        Optional<Match> matchesExists;
        String hql = "FROM Match where id = 1";
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            matchesExists = session.createQuery(hql).uniqueResultOptional();
            return matchesExists.isPresent();
        }
    }

    public List<Match> getByPage(int pageNumber) {
        int pageSize = 10;
        SessionFactory factory = connectionManager.getSessionFactory();
        Session session = null;
        List<Match> matches;
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

    public List<Match> getAll() {
        SessionFactory factory = connectionManager.getSessionFactory();
        List<Match> matches;
        String hql = "FROM Match";
        try (Session session = factory.getCurrentSession()) {
            session.beginTransaction();
            matches = session.createQuery(hql).list();
            session.getTransaction().commit();
        }
        return matches;
    }
}