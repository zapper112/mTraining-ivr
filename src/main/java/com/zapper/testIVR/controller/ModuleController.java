package com.zapper.testIVR.controller;

import com.zapper.testIVR.model.IVRResponse;

import org.springframework.stereotype.Controller;

@Controller
public class ModuleController implements BasicController {

  private Integer status = 800;
  //TODO : Get module menu by querying for the modules available in a Course
  private String responseMessage = "<response><playtext>Press 1 for Module 1. Press 2 for Module 2. Press 3 for Module 3. Press 9 to go back to Welcome Menu</playtext></response>";

  public IVRResponse service() {
    return new IVRResponse(status, responseMessage);
  }


}
