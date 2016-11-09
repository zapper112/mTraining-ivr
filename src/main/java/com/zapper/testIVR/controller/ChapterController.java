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
    if(event == null) {
      response.addHangup();
      return response.getXML();
    }
    return new UserUtil().getChapterForUser(new User(cid));
  }
}
