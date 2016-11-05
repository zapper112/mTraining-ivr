package com.zapper.testIVR.util;

import com.zapper.testIVR.model.Course;
import com.zapper.testIVR.model.User;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * Created by Satyarth on 1/11/16.
 */
public class DBUtilTest {


  @Test
  @Ignore
  public void getCourseIndexTest() {
   List<Course> courses = DBUtil.getCourseIndex();
    for(Course c : courses) {
      System.out.println("Id = " + c.getId());
      System.out.println("name = " + c.getName());
    }
  }

  @Test
  @Ignore
  public void getLastCompleteQuiz() {
    List<Integer> quizToStart = DBUtil.getQuizAndCompletedQuestions(new User("231313","blahblah"), "1");
    for(Integer i : quizToStart) {
      System.out.println(i);
    }
  }

  @Test
  @Ignore
  public void testGetContinuingQuestion() {
    System.out.println(DBUtil.getContinuingQuestion(null).getQuestionText());
  }

}