package com.zapper.testIVR.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Created by Satyarth on 1/11/16.
 */
public class HibernateUtil {

  public static Session getSession() {
    Configuration configuration = new Configuration().configure();
    SessionFactory sessionFactory = configuration.buildSessionFactory();
    return sessionFactory.openSession();
  }
}
