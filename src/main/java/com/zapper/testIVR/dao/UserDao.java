package com.zapper.testIVR.dao;

import com.zapper.testIVR.model.User;
import com.zapper.testIVR.model.UserQuizProgress;
import com.zapper.testIVR.model.UserResponse;
import com.zapper.testIVR.util.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import java.util.List;

/**
 * Created by Satyarth on 26/10/16.
 */
public class UserDao {

  private static Session session;
  /**
   * DAO method to add a user to the database if it doesn't exist. Update otherwise.
   * @param user : The user to be persisted in the database
   */
  public void addOrUpdateUser(User user) {
    session = HibernateUtil.getSession();
    Transaction transaction = session.beginTransaction();
    session.saveOrUpdate(user);
    transaction.commit();
    session.close();
  }

  /**
   * DAO method to return a User identified by its <code>callerId</code>
   * @param callerId
   * @return the user (if any) found from the data source
   */
  public User getUser(User user) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(User.class)
        .add(Restrictions.eq("callerId", user.getCallerId()));
    List<User> results = (List<User>) criteria.list();
    return (results.size() > 0 ) ? results.get(0) : null;
  }


  public static void saveUserQuizProgress(UserQuizProgress updatedUQP, UserQuizProgress outdatedUQP) {
    session = HibernateUtil.getSession();
    Transaction transaction = session.beginTransaction();
    session.delete(outdatedUQP);
    session.saveOrUpdate(updatedUQP);
    transaction.commit();
    session.close();
  }

  public static void saveUserResponse(UserResponse feedback) {
    session = HibernateUtil.getSession();
    Transaction transaction = session.beginTransaction();
    session.saveOrUpdate(feedback);
    transaction.commit();
    session.close();
  }

}
