package com.zapper.testIVR.util;

import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.User;
import com.zapper.testIVR.model.UserChapterProgress;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Satyarth on 7/11/16.
 */
public class UserUtilTest {

  private User user;

  @Test
  @Ignore
  public void testContinuingQuestion() {
    user = new User("9100571475");
    Chapter chapter = new Chapter(1);
    String question = UserUtil.continueQuiz(user, chapter);
    System.out.println(question);
  }

  @Test
  @Ignore
  public void testSaveUCP() {
    user = new User("9100571475");
    UserChapterProgress ucp = new UserChapterProgress(user, new Chapter(1));
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    session.save(ucp);
    tx.commit();
    session.close();
  }
}