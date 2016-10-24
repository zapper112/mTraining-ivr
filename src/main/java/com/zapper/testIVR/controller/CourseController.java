package com.zapper.testIVR.controller;

import com.zapper.testIVR.model.IVRResponse;

import org.springframework.stereotype.Controller;

@Controller
public class CourseController implements BasicController {

  private Integer DUMMY_STATUS = 800;
  private String
      DUMMY_RESPONSE =
      "<response><playtext>Press 1 for Course number 1. Press 2 for Course Number 2. Press 3 for Course Number 3. Press 9 to go back to Welcome Menu.</playtext></response>";

  public IVRResponse service() {
    return new IVRResponse(DUMMY_STATUS, DUMMY_RESPONSE);
  }


}
