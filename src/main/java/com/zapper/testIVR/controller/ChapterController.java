package com.zapper.testIVR.controller;

import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.User;
import com.zapper.testIVR.util.UserUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Satyarth on 8/11/16.
 */
@Controller
public class ChapterController {

  @ResponseBody
  @RequestMapping(value = "/chapter")
  public String chapterService(HttpServletRequest request) {
    Response response = new Response();
    String cid = request.getParameter("cid");
    String event = request.getParameter("event");
    String data = request.getParameter("data");
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    System.out.println("event = "+ event);
    System.out.println("cid = " + cid);
    System.out.println("data = " + data);
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
    if(event != null && event.equalsIgnoreCase("gotdtmf") && data != null && data.equals("1") ) {
      response.addPlayText("integrating the quiz sub module soon. Will also update U C P");
      response.addHangup();
      return response.getXML();
    }
    else if (event != null && event.equalsIgnoreCase("gotdtmf") && data != null && data.equals("2")) {
      response.addPlayText("Thank you for calling m Trainig. Will also update U C P");
      response.addHangup();
      return response.getXML();
    }
    return new UserUtil().getChapterForUser(new User(cid));
  }
}
