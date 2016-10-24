package com.zapper.testIVR.controller;

import com.zapper.testIVR.model.IVRResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Satyarth on 24/10/16.
 */

@Controller
public class Demo implements BasicController {

  public IVRResponse service() {
    return null;
  }

  @ResponseBody
  @RequestMapping(value = "/demo", method = RequestMethod.GET)
  public String demo() {
    String returnString = "<response><playtext>Welcome to M Training</playtext></response>";
    String tempString = "<html><h2>The Browser does understand this Schema</h2></html>";
    System.out.println(returnString);
    return returnString;
  }

}
