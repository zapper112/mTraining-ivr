package com.zapper.testIVR.util;

import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.SessionVariable;
import com.zapper.testIVR.model.User;

import java.security.Timestamp;

/**
 * Created by Satyarth on 7/11/16.
 */
public class SessionUtil {

  public void saveUserSession(User user, String sessionId) {
    SessionVariable sv = new SessionVariable(user, sessionId);
    DBUtil.saveUserSession(sv);
  }

  public Chapter getChapterForSession(User user, String sessionId, Integer chapterId) {
    if(chapterId != null) updateUserSessionWithChaper(user, sessionId, chapterId);
    SessionVariable sv = DBUtil.getSessionVariable(user, sessionId);
    return sv.getChapter();
  }

  private void updateUserSessionWithChaper(User user, String sessionId, Integer chapterId) {
    SessionVariable previousSessionVariable = DBUtil.getSessionVariable(user,sessionId);
    SessionVariable currentSessionVariable = new SessionVariable(user, sessionId,new Chapter(chapterId));
    DBUtil.updateSessionVariable(previousSessionVariable, currentSessionVariable);
  }
}
