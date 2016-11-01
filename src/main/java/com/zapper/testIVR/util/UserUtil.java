package com.zapper.testIVR.util;

import com.zapper.testIVR.dao.UserDao;
import com.zapper.testIVR.model.User;

/**
 * Created by shankar on 1/11/16.
 */
public class UserUtil {
  private static User user;

  public static boolean checkIfUserExists(String callerId) {
    user = new UserDao().getUser(callerId);
    if (user == null) return false;
    else return true;
  }

  public static void addOrUpdateUser(User user) {
    new UserDao().addOrUpdateUser(user);
  }
}
