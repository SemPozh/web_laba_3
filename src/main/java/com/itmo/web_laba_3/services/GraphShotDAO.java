package com.itmo.web_laba_3.services;

import com.itmo.web_laba_3.model.GraphShot;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class GraphShotDAO {
    public void save(GraphShot graphShot) {
        Session session = DatabaseManager.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(graphShot);
        transaction.commit();
        session.close();
    }
    public List<GraphShot> getShots(){
        return (List<GraphShot>) DatabaseManager.getSessionFactory().openSession().createQuery("from GraphShot").list();
    }

    public void clearAllShots(){
        Session session = DatabaseManager.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("delete from GraphShot").executeUpdate();
        transaction.commit();
        session.close();
    }
}
