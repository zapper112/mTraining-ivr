package com.zapper.testIVR.util;

import com.zapper.testIVR.model.Option;
import com.zapper.testIVR.model.Question;
import com.zapper.testIVR.model.Quiz;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * Created by Satyarth on 2/11/16.
 */
public class QuizUtilTest {

  @Test
  @Ignore
  public void testGetQuizzesForChapter() throws Exception {
    List<Quiz> quizzes = new QuizUtil().getQuizzesForChapter(1);
    for(Quiz quiz : quizzes) {
      System.out.println("id = " + quiz.getId());
      System.out.println("chapterId = " + quiz.getChapter().getId());
      System.out.println("numberOfQuestions = " + quiz.getNumberOfQuestions());
    }
  }

  @Test
  @Ignore
  public void testGetAllQuestionsForQuiz() throws Exception {
    List<Question> questions = new QuizUtil().getQuestionsForQuiz(2);
    for(Question question : questions) {
      System.out.println("id = " + question.getId());
      System.out.println("text = " + question.getQuestionText());
      System.out.println("correctOption = " + question.getCorrectOptionNo());
      System.out.println("quizId = " + question.getQuiz().getId());
    }
  }

  @Test
  @Ignore
  public void testGetOptionsForQuestion() throws Exception {
    List<Option> options = new QuizUtil().getOptionsForQuestion(1);
    for(Option option : options) {
      System.out.println("questionId = " + option.getQuestionId());
      System.out.println("optionNo = " + option.getOptionNo());
      System.out.println("optionText = " + option.getOptionText());
    }
  }

}