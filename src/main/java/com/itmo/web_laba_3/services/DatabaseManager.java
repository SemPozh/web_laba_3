package com.itmo.web_laba_3.services;
import jakarta.persistence.EntityManager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {
    private static EntityManagerFactory entityManagerFactory;
    public DatabaseManager(){}
    public static EntityManagerFactory getEntityManagerFactory(){
        if (entityManagerFactory==null){
            // Настройка свойств для EclipseLink
            Map<String, Object> properties = new HashMap<>();
            properties.put("jakarta.persistence.jdbc.driver", "org.postgresql.Driver");
            properties.put("jakarta.persistence.jdbc.url", "jdbc:postgresql://postgres:5432/lab3_user");
            properties.put("jakarta.persistence.jdbc.user", "lab3_user");
            properties.put("jakarta.persistence.jdbc.password", "lab3_user_password");
            properties.put("eclipselink.logging.level", "FINE");
            properties.put("eclipselink.ddl-generation", "create-tables");
            entityManagerFactory = Persistence.createEntityManagerFactory("lab3Unit", properties);
        }
        return entityManagerFactory;
    }

    public static EntityManager getEntityManager(){
        return getEntityManagerFactory().createEntityManager();
    }
}