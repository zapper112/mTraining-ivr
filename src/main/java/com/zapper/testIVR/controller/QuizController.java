package com.zapper.testIVR.controller;

import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.User;
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
    currentUser = new User(callerId, sessionId);
    return chooseAppropriateAction(currentUser, dtmf, quizAction, chapterNo);
   }

  private String chooseAppropriateAction(User currentUser, String dtmf, String quizAction, String chapterNo) {
    Response response = new Response();
    if(dtmf.equals("#")) {
      response.addHangup();
      return response.getXML();
    }

    if(quizAction == null) {
      UserUtil.saveAnswer(currentUser, chapterNo, dtmf);
    }
    return UserUtil.continueQuiz(currentUser, chapterNo);
  }
}
