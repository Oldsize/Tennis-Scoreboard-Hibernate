package com.example.hibernate_practice.utils;

import com.example.hibernate_practice.model.Player;
import org.h2.engine.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConnectionManager {
    private static final ConnectionManager INSTANCE = new ConnectionManager();

    SessionFactory sessionFactory = null;

    public static ConnectionManager getInstance() {
        return INSTANCE;
    }

    private ConnectionManager() {
    }

    public SessionFactory getSessionFactory() {
        sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Player.class)
                .buildSessionFactory();
        return sessionFactory;
    }
}