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
    servletRequest.addParameter("event","newcall");
    servletRequest.addParameter("cid","1425");
    servletRequest.addParameter("sid","1426");
    servletRequest.addParameter("data","1");
    System.out.println(welcomeController.startService(servletRequest));
  }
}