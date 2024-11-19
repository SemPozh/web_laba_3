package com.itmo.web_laba_3.services;

import com.itmo.web_laba_3.model.GraphShot;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class DatabaseManager {
    private static SessionFactory sessionFactory;
    public DatabaseManager(){}
    public static SessionFactory getSessionFactory(){
        if (sessionFactory==null){
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(GraphShot.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(builder.build());
        }
        return sessionFactory;

    }


}