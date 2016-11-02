package com.zapper.testIVR.service;

import com.zapper.testIVR.kookooJava.CollectDtmf;
import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.User;

/**
 * Created by shankar on 1/11/16.
 */
public class Home {
  Response response = new Response();

  public String startSession() {
    CollectDtmf collectDtmf = new CollectDtmf();
    collectDtmf.setMaxDigits(1);
    collectDtmf.addPlayText("Hello! Welcome to M-Training");
    collectDtmf.addPlayText("To start Course press 1");
    collectDtmf.addPlayText("To exit M-Training press 2");
    response.addCollectDtmf(collectDtmf);
    return response.getXML();
  }

  public String continueSession(User user) {
    response.addPlayText("Welcome back");
    response.addPlayText(startSession());
    response.addHangup();
    return response.getXML();
  }
}
