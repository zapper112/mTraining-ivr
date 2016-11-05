package com.zapper.testIVR.dao;

import com.zapper.testIVR.model.User;
import com.zapper.testIVR.model.UserQuizProgress;
import com.zapper.testIVR.model.UserResponse;

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
    System.out.println(userDao.retrieveUser("9100571475"));
  }

  @Test
  @Ignore
  public void testUserResponseSave() {
    UserResponse ur = new UserResponse("940",1,4, null);
    UserDao.saveUserResponse(ur);
  }

  @Test
  @Ignore
  public void testSaveUserFeedback() {
    UserResponse userResponse = new UserResponse("90",2,4,null);
    UserQuizProgress userQuizProgress = new UserQuizProgress("90",1,4,true);
    UserDao.saveUserFeedback(userQuizProgress);
  }

}