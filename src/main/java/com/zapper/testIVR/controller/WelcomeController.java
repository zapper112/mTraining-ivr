package com.zapper.testIVR.controller;

import com.zapper.testIVR.kookooJava.CollectDtmf;
import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.StandardMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WelcomeController implements BasicController {


  @RequestMapping(value = "/home", method = RequestMethod.GET)
  @ResponseBody
  public String startService(HttpServletRequest request) {
    Response response = new Response();
    String event = request.getParameter("event");

    if (event != null && event.toLowerCase().equals("newcall")) {
      CollectDtmf collectDtmf = new CollectDtmf();
      collectDtmf.setMaxDigits(1);
      collectDtmf.addPlayText(StandardMessage.welcome);
      collectDtmf.addPlayText(StandardMessage.beginCourse);
      collectDtmf.addPlayText(StandardMessage.exitTraining);
      response.addCollectDtmf(collectDtmf);
    } else if (event != null && event.toLowerCase().equals("gotdtmf")) {
      if (request.getParameter("data").equals("1")) {
        response.addPlayText(StandardMessage.oralMedicineCourseStart);
        CollectDtmf cd = new CollectDtmf();
        cd.addPlayText(StandardMessage.quizPrompt);
        response.addGotoNEXTURL("http://183.82.96.201:8100/testIVR/mtrain/quiz");
        response.addCollectDtmf(cd);
      } else if (request.getParameter("data").equals("2")) {
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
