package com.zapper.testIVR.dao;

import com.zapper.testIVR.model.User;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Satyarth on 26/10/16.
 */
public class UserDaoTest {

  UserDao userDao;

  @Test
  @Ignore
  public void testUserSave () {
    User user1 = new User();
    user1.setCallerId("9100571475");
    user1.setSessionId("newestSessionId");
    userDao = new UserDao();
    userDao.addOrUpdateUser(user1);
  }

  @Ignore
  @Test
  public void testUserRetrieve() {
    userDao = new UserDao();
    System.out.println(userDao.getUser("9100571475"));
  }

}