package com.zapper.testIVR.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.*;

/**
 * Created by Satyarth on 4/11/16.
 */
public class QuizControllerTest {

  private QuizController quizController;
  MockHttpServletRequest mockHttpServletRequest;

  @Test
  @Ignore
  public void testContinuingQuestion() {
    quizController = new QuizController();
    mockHttpServletRequest = new MockHttpServletRequest();
    mockHttpServletRequest.addParameter("cid","9100571475");
    //mockHttpServletRequest.addParameter("sid","123456");
    mockHttpServletRequest.addParameter("data","1");
    System.out.println(quizController.startQuizService(mockHttpServletRequest));
  }

}