package com.zapper.testIVR.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

/**
 * Created by Satyarth on 9/11/16.
 */
public class ChapterControllerTest {

  ChapterController chapterController;

  @Test
  @Ignore
  public void testChapterService() throws Exception {
    chapterController = new ChapterController();
    MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();
    mockHttpServletRequest.addParameter("event","gotDtmf");
    mockHttpServletRequest.addParameter("data","1");
    mockHttpServletRequest.addParameter("cid","90");
    System.out.println(chapterController.chapterService(mockHttpServletRequest));
  }
}