package com.zapper.testIVR.controller;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import javax.servlet.http.HttpServletRequest;

import static org.junit.Assert.*;

/**
 * Created by shankar on 1/11/16.
 */
public class WelcomeControllerTest {

  WelcomeController welcomeController = new WelcomeController();
  MockHttpServletRequest servletRequest = new MockHttpServletRequest();

  @Test
  @Ignore
  public void startServiceTest() {
    servletRequest.addParameter("event","NewCall");
    servletRequest.addParameter("cid","9100571478");
    servletRequest.addParameter("sid","resolve soon");
    welcomeController.startService(servletRequest);
  }
}