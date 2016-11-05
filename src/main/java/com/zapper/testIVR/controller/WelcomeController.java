package com.zapper.testIVR.controller;

import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.User;
import com.zapper.testIVR.service.Home;
import com.zapper.testIVR.util.CourseUtil;
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
      if (UserUtil.userExists(cid)) {
        UserUtil.addOrUpdateUser(new User(cid, sid));
        return new Home().continueSession(new User(cid, sid));
      } else {
        UserUtil.addOrUpdateUser(new User(cid, sid));
        return new Home().startSession();
      }
    } else if (event != null && event.toLowerCase().equals("gotdtmf")) {
      if (request.getParameter("data").equals("1")) {
        String returnXML = new CourseUtil().startCoursesFromBeginning();
        return returnXML;
      } else if (request.getParameter("data").equals("2")) {
        response.addPlayText("Thank you for calling M-Training");
        response.addHangup();
      } else {
        response.addPlayText("Sorry that is not a valid input");
        response.addHangup();
      }
    }
    return response.getXML();
  }
}
