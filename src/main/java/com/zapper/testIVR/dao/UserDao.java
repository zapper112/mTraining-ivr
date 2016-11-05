package com.zapper.testIVR.dao;

import com.zapper.testIVR.model.User;
import com.zapper.testIVR.model.UserFeedback;
import com.zapper.testIVR.model.UserQuizProgress;
import com.zapper.testIVR.model.UserResponse;
import com.zapper.testIVR.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

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
  public User retrieveUser(String callerId) {
    session = HibernateUtil.getSession();
    Transaction transaction = session.beginTransaction();
    User retrievedUser = session.get(User.class,callerId);
    transaction.commit();
    return retrievedUser;
  }

  public static String getQuizToStartQuery() {
    return "select coalesce(min(UQP.quizId),0) as quizToStart,\n"
        + "coalesce(UQP.questionsAnswered, 0) as questionsAnswered\n"
        + "from UserQuizProgress UQP\n"
        + "inner join Quiz Q\n"
        + "on UQP.quizId = Q.id\n"
        + "and Q.chapterId = :chapterId\n"
        + "where UQP.callerId = :callerId\n"
        + "and UQP.quizCompleted = false\n";
  }

  public static String getContinuingQuestionQuery() {
    return "select Q.id as questionId, Q.questionText as questionText \n"
        + "from Question Q\n"
        + "where Q.quizId = :quizToStart\n"
        + "order by Q.id\n";
  }

  public static void saveUserQuizProgress(UserQuizProgress uqp) {
    session = HibernateUtil.getSession();
    Transaction transaction = session.beginTransaction();
    session.saveOrUpdate(uqp);
    transaction.commit();
    session.close();
  }

  public static void saveUserResponse(UserResponse ur) {
    session = HibernateUtil.getSession();
    Transaction transaction = session.beginTransaction();
    session.saveOrUpdate(ur);
    transaction.commit();
    session.close();
  }

  public static void saveUserFeedback(UserFeedback feedback) {
    Class instanceClass = feedback.getClass();
    session = HibernateUtil.getSession();
    Transaction transaction = session.beginTransaction();
    session.saveOrUpdate(feedback);
    transaction.commit();
    session.close();
  }

}
