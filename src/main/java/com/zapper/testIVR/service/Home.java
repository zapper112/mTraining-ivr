package com.zapper.testIVR.service;

import com.zapper.testIVR.kookooJava.CollectDtmf;
import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.StandardMessage;
import com.zapper.testIVR.model.User;

/**
 * Created by shankar on 1/11/16.
 */
public class Home {
  Response response = new Response();

  public String startSession() {
    CollectDtmf collectDtmf = new CollectDtmf();
    collectDtmf.setMaxDigits(1);
    collectDtmf.addPlayText(StandardMessage.welcome);
    collectDtmf.addPlayText(StandardMessage.beginCourse);
    collectDtmf.addPlayText(StandardMessage.exitTraining);
    response.addCollectDtmf(collectDtmf);
    return response.getXML();
  }

  public String continueSession(User user) {
    response.addPlayText("User already found. This is good enough for P O C");
    response.addHangup();
    return response.getXML();
  }
}
