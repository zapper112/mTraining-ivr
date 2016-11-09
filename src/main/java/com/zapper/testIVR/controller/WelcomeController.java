package com.zapper.testIVR.controller;

import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.User;
import com.zapper.testIVR.service.Home;
import com.zapper.testIVR.service.StandardMessage;
import com.zapper.testIVR.util.CourseUtil;
import com.zapper.testIVR.util.SessionUtil;
import com.zapper.testIVR.util.UserUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class WelcomeController {


  @RequestMapping(value = "/home", method = RequestMethod.GET)
  @ResponseBody
  public String startService(HttpServletRequest request) {

    Response response = new Response();
    String event = request.getParameter("event");
    String sid = request.getParameter("sid");
    String cid = request.getParameter("cid");

    if (event != null && event.toLowerCase().equals("newcall")) {
      if (UserUtil.userExists(new User(cid))) {
        new SessionUtil().saveUserSession(new User(cid), sid);
        return new Home().continueSession();
      } else {
        UserUtil.addOrUpdateUser(new User(cid));
        new SessionUtil().saveUserSession(new User(cid), sid);
        return new Home().startSession();
      }
    } else if (event != null && event.toLowerCase().equals("gotdtmf")) {
      if (request.getParameter("data").equals("1")) {
        return new CourseUtil().redirectToChapterController(new User(cid));
      } else if (request.getParameter("data").equals("2")) {
        response.addPlayText(StandardMessage.THANK_USER);
        response.addHangup();
      } else {
        response.addPlayText(StandardMessage.INVALID_INPUT);
        response.addHangup();
      }
    }
    return response.getXML();
  }
}
