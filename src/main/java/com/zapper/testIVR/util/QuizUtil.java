package com.zapper.testIVR.util;

import com.zapper.testIVR.model.Option;
import com.zapper.testIVR.model.Question;
import com.zapper.testIVR.model.Quiz;

import java.util.List;

/**
 * Created by Satyarth on 2/11/16.
 */
public class QuizUtil {

  public List<Quiz> getQuizzesForChapter(Integer chapterId) {
    return DBUtil.getQuizzesForChapter(chapterId);
  }

  public List<Question> getQuestionsForQuiz(Integer quizId) {
    return DBUtil.getAllQuestionsForQuiz(quizId);
  }

  public List<Option> getOptionsForQuestion(Integer questionId) {
    return DBUtil.getOptionsForQuestion(questionId);
  }
}

