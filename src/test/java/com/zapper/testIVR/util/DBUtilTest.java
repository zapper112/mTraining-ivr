package com.zapper.testIVR.util;

import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.Course;
import com.zapper.testIVR.model.Module;
import com.zapper.testIVR.model.Quiz;
import com.zapper.testIVR.model.User;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
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
  public void testGetContinuingQuestion() {
    System.out.println(DBUtil.getContinuingQuestion(null).getQuestionText());
  }

  @Test
  @Ignore
  public void testInnerJoinInHibernate() {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Module.class);
    criteria.createAlias("course", "mc").add(Restrictions.eq("mc.id",1));
    List<Module> results = (List<Module>) criteria.list();
    for(Module m : results) {
      System.out.println(m.getCourse().getName());
    }
  }

  @Test
  @Ignore
  public void testFirstQuizInChapter() {
    Quiz quiz = DBUtil.getFirstQuizInChapter(new Chapter(1));
    System.out.println(quiz.getId());
    System.out.println(quiz.getNumberOfQuestions());
  }

}