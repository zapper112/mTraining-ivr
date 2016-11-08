package com.zapper.testIVR.controller;

import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.User;
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
    String quizAction = request.getParameter("quizAction");
    String chapterNo = request.getParameter("chapterNo");
    String dtmf = request.getParameter("data");
    callerId = request.getParameter("cid");
    sessionId = request.getParameter("sid");
    currentUser = new User(callerId);
    Chapter chapter = new SessionUtil().getChapterForSession(new User(callerId), sessionId, chapterNo);
    return chooseAppropriateAction(currentUser, dtmf, quizAction, chapter);
   }

  private String chooseAppropriateAction(User currentUser, String dtmf, String quizAction, Chapter chapter) {
    Response response = new Response();
    if(dtmf.equals("#")) {
      response.addHangup();
      return response.getXML();
    }

    if(quizAction == null) {
      UserUtil.saveAnswer(currentUser, chapter, dtmf);
    }
    return UserUtil.continueQuiz(currentUser, chapter);
  }
}
