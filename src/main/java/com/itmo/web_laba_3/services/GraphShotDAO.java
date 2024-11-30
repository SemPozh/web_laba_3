package com.itmo.web_laba_3.services;

import com.itmo.web_laba_3.model.GraphShot;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class GraphShotDAO {
    public void save(GraphShot graphShot) {
        EntityManager entityManager = DatabaseManager.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(graphShot);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
        }
    }
    public List<GraphShot> getShots(){
        try (EntityManager entityManager = DatabaseManager.getEntityManager()) {
            return entityManager.createQuery("SELECT g FROM GraphShot g", GraphShot.class).getResultList();
        }
    }

    public void clearAllShots(){
        EntityManager entityManager = DatabaseManager.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.createQuery("DELETE FROM GraphShot").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            entityManager.close();
        }
    }
}
