package com.itmo.web_laba_3.services;
import jakarta.persistence.EntityManager;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.HashMap;
import java.util.Map;

public class DatabaseManager {
    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("lab3Unit");
    public DatabaseManager(){}

    public EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }
}