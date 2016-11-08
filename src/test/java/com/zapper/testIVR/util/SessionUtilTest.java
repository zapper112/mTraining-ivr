package com.zapper.testIVR.util;

import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.SessionVariable;
import com.zapper.testIVR.model.User;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Satyarth on 7/11/16.
 */
public class SessionUtilTest {

  @Test
  @Ignore
  public void testSaveUserSession() throws Exception {
    new SessionUtil().saveUserSession(new User("9100571475"), "123456");
  }

  @Test
  @Ignore
  public void testUpdateFeatureInSV() {
    Chapter chapter = new SessionUtil().getChapterForSession(new User("90"),"135246", null);
    System.out.println(chapter.getId());
    System.out.println(chapter.getName());
  }

  @Test
  @Ignore
  public void testBasicSave() {
    SessionVariable sv = new SessionVariable(new User("90"),"007",new Chapter(1));
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    session.save(sv);
    tx.commit();
    session.close();
  }
}