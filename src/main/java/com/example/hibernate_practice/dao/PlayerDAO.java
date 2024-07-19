package com.example.hibernate_practice.dao;

import com.example.hibernate_practice.model.Player;
import com.example.hibernate_practice.utils.ConnectionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class PlayerDAO {
    private static final PlayerDAO INSTANCE = new PlayerDAO();

    private PlayerDAO() {
    }

    public static PlayerDAO getInstance() {
        return INSTANCE;
    }

    ConnectionManager connectionManager = ConnectionManager.getInstance();

    public Player savePlayer(Player player) {
        SessionFactory factory = connectionManager.getSessionFactory();
        Session session = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.saveOrUpdate(player);
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return player;
    }

    public Optional<Player> findPlayerByName(String name) {
        SessionFactory factory = connectionManager.getSessionFactory();
        Session session = null;
        String hql = "FROM Player WHERE name = :name";
        Optional<Player> findedPlayer = Optional.empty();
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            findedPlayer = session.createQuery(hql).setParameter("name", name).uniqueResultOptional();
            session.getTransaction().commit();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return findedPlayer;
    }
}