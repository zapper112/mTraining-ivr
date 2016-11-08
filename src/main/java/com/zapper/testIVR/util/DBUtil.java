package com.zapper.testIVR.util;

import com.zapper.testIVR.dao.UserDao;
import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.Course;
import com.zapper.testIVR.model.Message;
import com.zapper.testIVR.model.Module;
import com.zapper.testIVR.model.Option;
import com.zapper.testIVR.model.Question;
import com.zapper.testIVR.model.Quiz;
import com.zapper.testIVR.model.SessionVariable;
import com.zapper.testIVR.model.User;
import com.zapper.testIVR.model.UserFeedback;
import com.zapper.testIVR.model.UserQuizProgress;
import com.zapper.testIVR.model.UserResponse;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by shankar on 1/11/16.
 */
public class DBUtil {

  static List<Course> getCourseIndex() {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Course.class);
    return (List<Course>) criteria.list();
  }

  static List<Module> getAllModulesForCourse(Course course) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Module.class)
        .add(Restrictions.eq("course", course));
    return (List<Module>) criteria.list();
  }

  static List<Chapter> getChaptersForModule(Module module) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Chapter.class)
        .add(Restrictions.eq("module",module));
    return (List<Chapter>) criteria.list();
  }

  static List<Message> getMessagesForChapter(Chapter chapter) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Message.class)
        .add(Restrictions.eq("chapter", chapter));
    return (List<Message>) criteria.list();
  }

  static List<Quiz> getQuizzesForChapter(Chapter chapter) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Quiz.class)
        .add(Restrictions.eq("chapter", chapter));
    return (List<Quiz>) criteria.list();
  }

  static List<Question> getQuestionsForQuiz(Quiz quiz) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Question.class);
    criteria.add(Restrictions.eq("quiz", quiz));
    return (List<Question>) criteria.list();
  }

  static List<Option> getOptionsForQuestion(Question question) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Option.class)
        .add(Restrictions.eq("question", question));
    return (List<Option>) criteria.list();
  }

  static UserQuizProgress getUserQuizProgress(User user, Chapter chapter) {
    if(!recordPresentInUQP(user, chapter)) {
      createNewUQP(user,chapter);
    }
    Criteria criteria = HibernateUtil.getSession().createCriteria(UserQuizProgress.class)
        .add(Restrictions.eq("user",user))
        .add(Restrictions.eq("quizCompleted",false));
    criteria.createAlias("quiz","uq")
        .add(Restrictions.eq("uq.chapter",chapter));
    List<UserQuizProgress> results = (List<UserQuizProgress>) criteria.list();
    if(results.size() == 0) {
      //This means that the user has already completed all the quizzes
      return null;
    }
    return results.get(0);
  }

  static Question getContinuingQuestion(UserQuizProgress uqp) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Question.class)
        .add(Restrictions.eq("quiz",uqp.getQuiz()))
        .addOrder(Order.asc("id"))
        .setFirstResult(uqp.getQuestionsAnswered())
        .setMaxResults(1);
    List<Question> results = (List<Question>) criteria.list();
    return results.get(0);
  }

  static void saveAnswer(User user, Chapter chapter, String dtmf) {
    UserQuizProgress userQuizProgress = DBUtil.getUserQuizProgress(user, chapter);
    Quiz quiz = userQuizProgress.getQuiz();
    Integer questionsAnswered = userQuizProgress.getQuestionsAnswered();
    Integer totalNumberOfQuestions = getQuestionsForQuiz(quiz).size();
    Boolean quizCompleted = (questionsAnswered + 1 == totalNumberOfQuestions);
    Question question = getContinuingQuestion(userQuizProgress);
    UserQuizProgress
        uqp =
        new UserQuizProgress(user, quiz, questionsAnswered + 1, quizCompleted);
    UserResponse
        ur =
        new UserResponse(user, question, Integer.valueOf(dtmf));
    UserDao.saveUserQuizProgress(uqp, userQuizProgress);
    UserDao.saveUserResponse(ur);
  }

  private static Boolean recordPresentInUQP(User user, Chapter chapter) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(UserQuizProgress.class)
        .add(Restrictions.eq("user", user))
        .createAlias("quiz","q")
        .add(Restrictions.eq("q.chapter",chapter));
    List results = criteria.list();
    return (results.size() >= 1);
  }

  private static void createNewUQP(User user, Chapter chapter) {
    Quiz quiz = getFirstQuizInChapter(chapter);
    UserQuizProgress uqp = new UserQuizProgress(user,quiz,0,false);
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    session.save(uqp);
    tx.commit();
    session.close();
  }

  private static Quiz getFirstQuizInChapter(Chapter chapter) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Quiz.class)
        .add(Restrictions.eq("chapter",chapter))
        .addOrder(Order.asc("id"))
        .setMaxResults(1);
    List<Quiz> results = (List<Quiz>) criteria.list();
    return (results.size() > 0) ? results.get(0) : null;
  }

  static void saveUserSession(SessionVariable sv) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    session.save(sv);
    tx.commit();
    session.close();
  }

  static SessionVariable getSessionVariable(User user, String sessionId) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(SessionVariable.class)
        .add(Restrictions.eq("user",user))
        .add(Restrictions.eq("sessionId",sessionId));
    List<SessionVariable> results = (List<SessionVariable>) criteria.list();
    return results.get(0);
  }

  static void updateSessionVariable (SessionVariable outdatedSV, SessionVariable updatedSV) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    session.delete(outdatedSV);
    session.save(updatedSV);
    tx.commit();
    session.close();
  }

}
