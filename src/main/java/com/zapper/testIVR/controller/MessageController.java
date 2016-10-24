package com.zapper.testIVR.controller;

import com.zapper.testIVR.model.IVRResponse;

import org.springframework.stereotype.Controller;

@Controller
public class MessageController implements BasicController {

  private Integer responseStatus = 800;
  private String responseMessage = "<response><playtext>Welcome to the message level </playtext></response>";

  public IVRResponse service() {
    return new IVRResponse(responseStatus, responseMessage);
  }
}
