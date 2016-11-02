package com.zapper.testIVR.dao;

/**
 * Created by Satyarth on 2/11/16.
 */
public class QuizDao {

  public static String getQuizzesInChapterQuery() {
    return "select Q.id as id, Q.numberOfQuestions as numberOfQuestions from Quiz Q where Q.chapterId = :chapterId order by Q.id";
  }

  public static String getQuestionsInQuizQuery() {
    return "select Q.id as id, Q.questionText as questionText, Q.correctOptionNo as correctOptionNo from Question Q where Q.quizId = :quizId order by Q.id";
  }

  public static String getOptionsInQuestionQuery() {
    return "select O.optionNo as optionNo, O.optionText as optionText from Option O where O.questionId = :questionId order by O.optionNo";
  }
}
