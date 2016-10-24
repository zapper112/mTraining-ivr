package com.zapper.testIVR.controller;

import com.zapper.testIVR.model.IVRResponse;

import org.springframework.stereotype.Controller;

@Controller
public class WelcomeController implements BasicController{

  private Integer status = 0;
  private String welcomeTree = "<response><playtext>Welcome to M-Training. Press 1 to access courses. Press 2 to exit.</playtext></response>";

  public IVRResponse service() {
    return new IVRResponse(status,welcomeTree);
  }
}
