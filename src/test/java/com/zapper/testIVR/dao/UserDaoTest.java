package com.zapper.testIVR.dao;

import com.zapper.testIVR.model.Question;
import com.zapper.testIVR.model.Quiz;
import com.zapper.testIVR.model.User;
import com.zapper.testIVR.model.UserQuizProgress;
import com.zapper.testIVR.model.UserResponse;

import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Satyarth on 26/10/16.
 */
public class UserDaoTest {

  private UserDao userDao;
  private User user;

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
    UserResponse ur = new UserResponse(new User(), new Question(),4, null);
    UserDao.saveUserResponse(ur);
  }

  @Test
  @Ignore
  public void testSaveUserFeedback() {
    user = new User("667788","2498241");
    UserResponse userResponse = new UserResponse(new User(),new Question(),4,null);
    UserQuizProgress userQuizProgress = new UserQuizProgress(user,new Quiz(1,null,4),4,true);
    UserDao.saveUserFeedback(userQuizProgress);
  }

}