package com.zapper.testIVR.controller;

import com.zapper.testIVR.dao.UserDao;
import com.zapper.testIVR.kookooJava.CollectDtmf;
import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.StandardMessage;
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
public class WelcomeController implements BasicController {


  @RequestMapping(value = "/home", method = RequestMethod.GET)
  @ResponseBody
  public String startService(HttpServletRequest request) {

    Response response = new Response();
    String event = request.getParameter("event");
    String sid = request.getParameter("sid");
    String cid = request.getParameter("cid");

    if (event != null && event.toLowerCase().equals("newcall")) {
      if(UserUtil.checkIfUserExists(cid)) {
        UserUtil.addOrUpdateUser(new User(cid,sid));
        return new Home().continueSession(new User(cid,sid));
      } else {
        UserUtil.addOrUpdateUser(new User(cid,sid));
        return new Home().startSession();}
    }

      else if (event != null && event.toLowerCase().equals("gotdtmf")) {
      if (request.getParameter("data").equals("1")) {
        String returnXML = new CourseUtil().startCoursesFromBeginning();
        return returnXML;
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
