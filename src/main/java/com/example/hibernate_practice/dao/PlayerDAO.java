package com.example.hibernate_practice.dao;

import com.example.hibernate_practice.model.Player;
import com.example.hibernate_practice.utils.ConnectionManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PlayerDAO {
    private static final PlayerDAO INSTANCE = new PlayerDAO();

    private PlayerDAO() {
    }

    public static PlayerDAO getInstance() {
        return INSTANCE;
    }

    ConnectionManager connectionManager = ConnectionManager.getInstance();

    public Player createPlayer(Player player) {
        SessionFactory factory = connectionManager.getSessionFactory();
        Session session = null;
        Player created_player = null;
        try {
            session = factory.getCurrentSession();
            session.beginTransaction();
            created_player = new Player();
            created_player.setName(player.getName());
            session.save(created_player);
            session.getTransaction().commit();
        } finally {
            factory.close();
            session.close();
        }
        return created_player;
    }
}