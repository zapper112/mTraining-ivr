package com.zapper.testIVR.util;

import com.zapper.testIVR.dao.UserDao;
import com.zapper.testIVR.kookooJava.CollectDtmf;
import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.Option;
import com.zapper.testIVR.model.Question;
import com.zapper.testIVR.model.User;
import com.zapper.testIVR.model.UserQuizProgress;

import java.util.List;

/**
 * Created by shankar on 1/11/16.
 */
public class UserUtil {
  private static User user;

  public static boolean userExists(String callerId) {
    user = new UserDao().retrieveUser(callerId);
    if (user == null) return false;
    else return true;
  }

  public static void addOrUpdateUser(User user) {
    new UserDao().addOrUpdateUser(user);
  }

  public static String continueQuiz(User user, Chapter chapter) {
    UserQuizProgress userQuizProgress = DBUtil.getUserQuizProgress(user, chapter);
    if(userQuizProgress == null) {
      return allQuizzesTakenInChapter();
    }
    Question question = DBUtil.getContinuingQuestion(userQuizProgress);
    List<Option> options = DBUtil.getOptionsForQuestion(question);
    Response response = new Response();
    CollectDtmf cd = new CollectDtmf();
    cd.addPlayText(question.getQuestionText());
    for(Option option : options) {
      cd.addPlayText("Option number - " + option.getOptionNo());
      cd.addPlayText(option.getOptionText());
    }
    response.addCollectDtmf(cd);
    return response.getXML();
  }

  public static void saveAnswer(User user, Chapter chapter, String dtmf) {
    DBUtil.saveAnswer(user, chapter, dtmf);
  }

  private static String allQuizzesTakenInChapter() {
    Response response = new Response();
    response.addPlayText("you have already attempted all quizzes in this chapter");
    response.addHangup();
    return response.getXML();
  }
}
