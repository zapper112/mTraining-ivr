package com.zapper.testIVR.controller;

import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.StandardMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Satyarth on 25/10/16.
 */
@Controller
public class QuizController implements BasicController {

  @RequestMapping(value = "/quiz", method = RequestMethod.GET)
  @ResponseBody
  public String startQuizService(HttpServletRequest request) {
    String event = request.getParameter("event");
    Response response = new Response();

    if (event != null && event.toLowerCase().equals("gotdtmf")) {
      if (request.getParameter("data").equals("1")) {
        response.addGotoNEXTURL("http://183.82.96.201:8100/testIVR/mtrain/startquiz?event=gotdtmf");
      } else if (request.getParameter("data").equals("2")) {
        response.addGotoNEXTURL("http://183.82.96.201:8100/testIVR/mtrain/home?event=gotdtmf&data=1");
      } else if (request.getParameter("data").equals("3")) {
        response.addPlayText(StandardMessage.callHangupValid);
        response.addHangup();
      } else {
        response.addPlayText(StandardMessage.callHangupInvalid);
        response.addHangup();
      }
    }
    return response.getXML();
  }

}
