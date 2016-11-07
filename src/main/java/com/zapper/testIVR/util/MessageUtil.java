package com.zapper.testIVR.util;

import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.Message;

import java.util.List;

/**
 * Created by Satyarth on 2/11/16.
 */
public class MessageUtil {

  public List<Message> getMessagesForChapter(Chapter chapter) {
    return DBUtil.getMessagesForChapter(chapter);
  }
}
