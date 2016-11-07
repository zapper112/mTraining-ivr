package com.zapper.testIVR.util;

import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.User;

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
    String question = new UserUtil().continueQuiz(user, chapter);
    System.out.println(question);
  }
}