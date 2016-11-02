package com.zapper.testIVR.dao;

/**
 * Created by Satyarth on 2/11/16.
 */
public class MessageDao {

  public static String getMessagesInChapterQuery() {
    return "select M.id as id, M.content as content from Message M where M.chapterId = :chapterId order by M.id";
  }
}
