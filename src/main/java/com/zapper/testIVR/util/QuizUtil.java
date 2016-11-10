package com.zapper.testIVR.util;

import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.Option;
import com.zapper.testIVR.model.Question;
import com.zapper.testIVR.model.Quiz;
import com.zapper.testIVR.model.User;

import java.util.List;

/**
 * Created by Satyarth on 2/11/16.
 */
public class QuizUtil {

  public List<Quiz> getQuizzesForChapter(Chapter chapter) {
    return DBUtil.getQuizzesForChapter(chapter);
  }

  public List<Question> getQuestionsForQuiz(Quiz quiz) {
    return DBUtil.getQuestionsForQuiz(quiz);
  }

  public List<Option> getOptionsForQuestion(Question question) {
    return DBUtil.getOptionsForQuestion(question);
  }

  public String getCurrentSessionId(User user) {
    return DBUtil.getCurrentSessionId(user);
  }
}

