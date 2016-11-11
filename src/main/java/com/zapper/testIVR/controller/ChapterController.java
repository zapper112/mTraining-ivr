package com.zapper.testIVR.controller;

import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.User;
import com.zapper.testIVR.util.ChapterUtil;
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
    if(event != null && event.equalsIgnoreCase("gotdtmf") && data != null && data.equals("1") ) {
      new ChapterUtil().updateUCPwithChapter(new User(cid));
      Chapter lastCompletedChapter = new ChapterUtil().getLastCompletedChapter(new User(cid));
      response.addGotoNEXTURL("http://183.82.96.201:8100/testIVR/mtrain/quiz?cid=" + cid);
      return response.getXML();
    }
    else if (event != null && event.equalsIgnoreCase("gotdtmf") && data != null && data.equals("2")) {
      new ChapterUtil().updateUCPwithChapter(new User(cid));
      response.addPlayText("Thank you for calling m Trainig.");
      response.addHangup();
      return response.getXML();
    }
    return new UserUtil().getChapterForUser(new User(cid));
  }
}
