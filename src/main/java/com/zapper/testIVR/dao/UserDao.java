package com.zapper.testIVR.dao;

import com.zapper.testIVR.model.User;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * Created by Satyarth on 26/10/16.
 */
public class UserDao {

  public void addUser(User user) {
    Configuration configuration = new Configuration().configure();
    SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    session.saveOrUpdate(user);
    transaction.commit();
    session.close();
  }

  public User getUser(String callerId) {
    Configuration configuration = new Configuration().configure();
    SessionFactory sessionFactory = configuration.buildSessionFactory();
    Session session = sessionFactory.openSession();
    Transaction transaction = session.beginTransaction();
    User retrievedUser = session.get(User.class,callerId);
    transaction.commit();
    return retrievedUser;
  }
}
