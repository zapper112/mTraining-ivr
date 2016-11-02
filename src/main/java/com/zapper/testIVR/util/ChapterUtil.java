package com.zapper.testIVR.util;

import com.zapper.testIVR.model.Chapter;

import java.util.List;

/**
 * Created by Satyarth on 2/11/16.
 */
public class ChapterUtil {

  public List<Chapter> getChaptersForModule(Integer moduleId) {
    return DBUtil.getChaptersForModule(moduleId);
  }
}
