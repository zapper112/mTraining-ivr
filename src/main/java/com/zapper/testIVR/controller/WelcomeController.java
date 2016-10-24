package com.zapper.testIVR.controller;

import com.zapper.testIVR.kookooJava.CollectDtmf;
import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.StandardMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class WelcomeController implements BasicController {


  @RequestMapping(value = "/home", method = RequestMethod.GET)
  @ResponseBody
  public String startService() {
    Response response = new Response();
    CollectDtmf collectDtmf = new CollectDtmf();
    collectDtmf.setMaxDigits(1);
    collectDtmf.addPlayText(StandardMessage.welcome);
    collectDtmf.addPlayText(StandardMessage.beginCourse);
    collectDtmf.addPlayText(StandardMessage.exitTraining);
    response.addCollectDtmf(collectDtmf);
    System.out.println(response.getXML());
    return response.getXML();
  }
}
