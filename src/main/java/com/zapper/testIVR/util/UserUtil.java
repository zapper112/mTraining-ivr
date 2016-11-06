package com.zapper.testIVR.util;

import com.zapper.testIVR.dao.UserDao;
import com.zapper.testIVR.kookooJava.CollectDtmf;
import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.Option;
import com.zapper.testIVR.model.Question;
import com.zapper.testIVR.model.User;

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

  public static String continueQuiz(User user, String chapterId) {
    List<Integer> quizAndCompletedQuestions =  DBUtil.getQuizAndCompletedQuestions(user, chapterId);
    Question question = DBUtil.getContinuingQuestion(null);
    List<Option> options = DBUtil.getOptionsForQuestion(question.getId());
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

  public static void saveAnswer(User user, String chapterId, String dtmf) {
    DBUtil.saveAnswer(user, chapterId, dtmf);
  }
}
