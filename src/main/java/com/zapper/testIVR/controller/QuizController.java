package com.zapper.testIVR.controller;

import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.User;
import com.zapper.testIVR.util.QuizUtil;
import com.zapper.testIVR.util.SessionUtil;
import com.zapper.testIVR.util.UserUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Satyarth on 3/11/16.
 */
@Controller
public class QuizController {

  private User currentUser;
  private String callerId;
  private String sessionId;

  @RequestMapping(value = "/quiz")
  @ResponseBody
   public String startQuizService(HttpServletRequest request) {
    String dtmf = request.getParameter("data"); //dtmf will be null on the first redirect from Chapter Controller
    callerId = request.getParameter("cid");
    sessionId = new QuizUtil().getCurrentSessionId(new User(callerId));
    currentUser = new User(callerId);
    Chapter chapter = new UserUtil().getChapterForQuiz(new User(callerId));
    return chooseAppropriateAction(currentUser, dtmf, chapter);
   }

  private String chooseAppropriateAction(User currentUser, String dtmf, Chapter chapter) {
    //dtmf equals null for the redirect from chapter controller
    Response response = new Response();
    if(dtmf != null && dtmf.equals("#")) {
      response.addHangup();
      return response.getXML();
    }

    if(dtmf != null) {
      UserUtil.saveAnswer(currentUser, chapter, dtmf);
    }
    return UserUtil.continueQuiz(currentUser, chapter);
  }
}
