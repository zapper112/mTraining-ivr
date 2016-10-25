package com.zapper.testIVR.service;

import com.zapper.testIVR.kookooJava.CollectDtmf;
import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.StandardMessage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Satyarth on 25/10/16.
 */
@Controller
public class QuizStart {

  @ResponseBody
  @RequestMapping(value = "/startquiz", method = RequestMethod.GET)
  public String startQuiz(HttpServletRequest request) {
    String event = request.getParameter("event");
    Response response = new Response();
    List<CollectDtmf> collectDtmfList = new ArrayList<CollectDtmf>();
    CollectDtmf quizQuestion1 = new CollectDtmf();
    CollectDtmf quizQuestion2 = new CollectDtmf();

    if (event != null && event.toLowerCase().equals("gotdtmf")) {
      response.addPlayText(StandardMessage.quizStarted);
      quizQuestion1.addPlayText(StandardMessage.question1);
      quizQuestion2.addPlayText(StandardMessage.question2);
      response.addCollectDtmf(quizQuestion1);
      response.addCollectDtmf(quizQuestion2);
      response.addPlayText(StandardMessage.quizEnded);
      response.addHangup();
    }
    System.out.println(response.getXML());
    return response.getXML();
  }
}
