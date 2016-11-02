package com.zapper.testIVR.util;

import com.zapper.testIVR.dao.ChapterDao;
import com.zapper.testIVR.dao.CourseDao;
import com.zapper.testIVR.dao.MessageDao;
import com.zapper.testIVR.dao.ModuleDao;
import com.zapper.testIVR.dao.QuizDao;
import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.Course;
import com.zapper.testIVR.model.Message;
import com.zapper.testIVR.model.Module;
import com.zapper.testIVR.model.Option;
import com.zapper.testIVR.model.Question;
import com.zapper.testIVR.model.Quiz;

import org.hibernate.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by shankar on 1/11/16.
 */
public class DBUtil {

  static List<Course> getCourseIndex() {
    String hibernateQuery = CourseDao.getCourseIndexQuery();
    Query query = HibernateUtil.getSession().createQuery(hibernateQuery);
    List<Course> results = query.list();
    Iterator it = results.iterator();
    List<Course> courses = new ArrayList<Course>();
    while (it.hasNext()) {
      Object[] objects = (Object[]) it.next();
      Integer courseId = (Integer) objects[0];
      String courseName = (String) objects[1];
      courses.add(new Course(courseId, courseName));
    }
    return courses;
  }

  static List<Module> getAllModulesForCourse(Integer courseId) {
    String modulesQuery = ModuleDao.getModulesInCourseQuery();
    Query
        query =
        HibernateUtil.getSession().createQuery(modulesQuery).setParameter("courseId", courseId);
    List<Module> results = query.list();
    Iterator it = results.iterator();
    List<Module> modules = new ArrayList<Module>();
    while (it.hasNext()) {
      Object[] objects = (Object[]) it.next();
      Integer moduleId = (Integer) objects[0];
      String moduleName = (String) objects[1];
      modules.add(new Module(moduleId, moduleName, courseId));
    }
    return modules;
  }

  static List<Chapter> getChaptersForModule(Integer moduleId) {
    String chapterQuery = ChapterDao.getChaptersInModuleQuery();
    Query
        query =
        HibernateUtil.getSession().createQuery(chapterQuery).setParameter("moduleId", moduleId);
    List<Chapter> results = query.list();
    Iterator it = results.iterator();
    List<Chapter> chapters = new ArrayList<Chapter>();
    while (it.hasNext()) {
      Object[] objects = (Object[]) it.next();
      Integer chatperId = (Integer) objects[0];
      String chapterName = (String) objects[1];
      chapters.add(new Chapter(chatperId, chapterName, moduleId));
    }
    return chapters;
  }

  static List<Message> getMessagesForChapter(Integer chapterId) {
    String messageQuery = MessageDao.getMessagesInChapterQuery();
    Query
        query =
        HibernateUtil.getSession().createQuery(messageQuery).setParameter("chapterId", chapterId);
    List<Message> results = query.list();
    Iterator it = results.iterator();
    List<Message> messages = new ArrayList<Message>();
    while (it.hasNext()) {
      Object[] objects = (Object[]) it.next();
      Integer messageId = (Integer) objects[0];
      String messageContent = (String) objects[1];
      messages.add(new Message(messageId, messageContent, chapterId));
    }
    return messages;
  }

  static List<Quiz> getQuizzesForChapter(Integer chapterId) {
    String quizQuery = QuizDao.getQuizzesInChapterQuery();
    Query query = HibernateUtil.getSession().createQuery(quizQuery).setParameter("chapterId",chapterId);
    List<Quiz> results = query.list();
    Iterator it = results.iterator();
    List<Quiz> quizzes = new ArrayList<Quiz>();
    while(it.hasNext()) {
      Object[] objects = (Object[]) it.next();
      Integer quizId = (Integer) objects[0];
      Integer numberOfQuestions = (Integer) objects[1];
      quizzes.add(new Quiz(quizId,chapterId,numberOfQuestions));
    }
    return quizzes;
  }

  static List<Question> getAllQuestionsForQuiz(Integer quizId) {
    String questionQuery = QuizDao.getQuestionsInQuizQuery();
    Query query = HibernateUtil.getSession().createQuery(questionQuery).setParameter("quizId",quizId);
    List<Question> results = query.list();
    Iterator it = results.iterator();
    List<Question> questions = new ArrayList<Question>();
    while(it.hasNext()) {
      Object[] objects = (Object[]) it.next();
      Integer questionId = (Integer) objects[0];
      String questionText = (String) objects[1];
      Integer correctOption = (Integer) objects[2];
      questions.add(new Question(questionId, quizId, questionText, correctOption));
    }
    return questions;
  }

  static List<Option> getOptionsForQuestion(Integer questionId) {
    String optionQuery = QuizDao.getOptionsInQuestionQuery();
    Query query = HibernateUtil.getSession().createQuery(optionQuery).setParameter("questionId",questionId);
    List<Option> results = query.list();
    Iterator it = results.iterator();
    List<Option> options = new ArrayList<Option>();
    while(it.hasNext()) {
      Object[] objects = (Object[]) it.next();
      Integer optionNo = (Integer) objects[0];
      String optionText = (String) objects[1];
      options.add(new Option(optionNo,optionText,questionId));
    }
    return options;
  }

}
