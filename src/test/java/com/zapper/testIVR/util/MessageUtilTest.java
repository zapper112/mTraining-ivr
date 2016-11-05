package com.zapper.testIVR.util;

import com.zapper.testIVR.model.Message;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Satyarth on 2/11/16.
 */
public class MessageUtilTest {

  @Test
  @Ignore
  public void testGetMessagesForChapter() throws Exception {
    List<Message> messages = new MessageUtil().getMessagesForChapter(2);
    for (Message message : messages) {
      System.out.println("Id = " + message.getId());
      System.out.println("Content = " + message.getContent());
      System.out.println("ChapterId = " + message.getChapterId());
    }
  }
}