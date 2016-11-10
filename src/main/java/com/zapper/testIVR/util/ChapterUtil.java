package com.zapper.testIVR.util;

import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.Module;
import com.zapper.testIVR.model.User;

import java.util.List;

/**
 * Created by Satyarth on 2/11/16.
 */
public class ChapterUtil {

  public List<Chapter> getChaptersForModule(Module module) {
    return DBUtil.getChaptersForModule(module);
  }

  public void updateUCPwithChapter(User user) {
    DBUtil.updateUCPwithChapter(user);
  }

  public Chapter getLastCompletedChapter(User user) {
    return DBUtil.getLastCompletedChapter(user);
  }
}
