package com.zapper.testIVR.controller;

import com.zapper.testIVR.dao.UserDao;
import com.zapper.testIVR.kookooJava.CollectDtmf;
import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.StandardMessage;
import com.zapper.testIVR.model.User;
import com.zapper.testIVR.service.Home;
import com.zapper.testIVR.util.UserUtil;

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
    String sid = request.getParameter("sid");
    String cid = request.getParameter("cid");
    System.out.println("event = " + event);
    System.out.println("sid = " + sid);
    System.out.println("cid = " + cid);

    if (event != null && event.toLowerCase().equals("newcall")) {
      if(UserUtil.checkIfUserExists(cid)) {
        System.out.println("\nUser already present in database. Therefore updated.\n");
        UserUtil.addOrUpdateUser(new User(cid,sid));
        return new Home().continueSession(new User(cid,sid));
      } else {
        UserUtil.addOrUpdateUser(new User(cid,sid));
        System.out.println("\nUser added to database\n");
        return new Home().startSession();}
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
