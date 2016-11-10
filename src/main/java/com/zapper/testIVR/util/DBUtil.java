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
import com.zapper.testIVR.model.UserChapterProgress;
import com.zapper.testIVR.model.UserQuizProgress;
import com.zapper.testIVR.model.UserResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shankar on 1/11/16.
 */
public class DBUtil {

  static List<Course> getCourseIndex() {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Course.class);
    return (List<Course>) criteria.list();
  }

  static List<Module> getModulesForCourse(Course course) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Module.class)
        .add(Restrictions.eq("course", course))
        .addOrder(Order.asc("id"));
    return (List<Module>) criteria.list();
  }

  static List<Chapter> getChaptersForModule(Module module) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Chapter.class)
        .add(Restrictions.eq("module", module))
        .addOrder(Order.asc("id"));
    return (List<Chapter>) criteria.list();
  }

  static List<Message> getMessagesForChapter(Chapter chapter) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Message.class)
        .add(Restrictions.eq("chapter", chapter))
        .addOrder(Order.asc("id"));
    return (List<Message>) criteria.list();
  }

  static List<Quiz> getQuizzesForChapter(Chapter chapter) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Quiz.class)
        .add(Restrictions.eq("chapter", chapter))
        .addOrder(Order.asc("id"));
    return (List<Quiz>) criteria.list();
  }

  static List<Question> getQuestionsForQuiz(Quiz quiz) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Question.class)
        .add(Restrictions.eq("quiz", quiz))
        .addOrder(Order.asc("id"));
    return (List<Question>) criteria.list();
  }

  static List<Option> getOptionsForQuestion(Question question) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Option.class)
        .add(Restrictions.eq("question", question))
        .addOrder(Order.asc("optionNo"));
    return (List<Option>) criteria.list();
  }

  static UserQuizProgress getUserQuizProgress(User user, Chapter chapter) {
    if (!recordPresentInUQP(user, chapter)) {
      createNewUQP(user, chapter);
    }
    Criteria criteria = HibernateUtil.getSession().createCriteria(UserQuizProgress.class)
        .add(Restrictions.eq("user", user))
        .add(Restrictions.eq("quizCompleted", false));
    criteria.createAlias("quiz", "uq")
        .add(Restrictions.eq("uq.chapter", chapter));
    List<UserQuizProgress> results = (List<UserQuizProgress>) criteria.list();
    if (results.size() == 0) {
      //This means that either the user has already completed all the quizzes or is currently at finished state of a quiz
      return getNextUserQuizProgress(user, chapter);
    }
    return results.get(0);
  }

  private static UserQuizProgress getNextUserQuizProgress(User user, Chapter chapter) {
    return null;
  }

  static Question getContinuingQuestion(UserQuizProgress uqp) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Question.class)
        .add(Restrictions.eq("quiz", uqp.getQuiz()))
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
    UserQuizProgress uqp = new UserQuizProgress(user, quiz, questionsAnswered + 1, quizCompleted);
    UserResponse ur = new UserResponse(user, question, Integer.valueOf(dtmf));
    UserDao.saveUserQuizProgress(uqp, userQuizProgress);
    UserDao.saveUserResponse(ur);
  }

  private static Boolean recordPresentInUQP(User user, Chapter chapter) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(UserQuizProgress.class)
        .add(Restrictions.eq("user", user))
        .createAlias("quiz", "q")
        .add(Restrictions.eq("q.chapter", chapter));
    List results = criteria.list();
    return (results.size() >= 1);
  }

  private static void createNewUQP(User user, Chapter chapter) {
    Quiz quiz = getFirstQuizInChapter(chapter);
    UserQuizProgress uqp = new UserQuizProgress(user, quiz, 0, false);
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    session.save(uqp);
    tx.commit();
    session.close();
  }

  private static Quiz getFirstQuizInChapter(Chapter chapter) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Quiz.class)
        .add(Restrictions.eq("chapter", chapter))
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
        .add(Restrictions.eq("user", user))
        .add(Restrictions.eq("sessionId", sessionId));
    List<SessionVariable> results = (List<SessionVariable>) criteria.list();
    return results.get(0);
  }

  static void updateSessionVariable(SessionVariable outdatedSV, SessionVariable updatedSV) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    session.delete(outdatedSV);
    session.save(updatedSV);
    tx.commit();
    session.close();
  }

  public static Chapter getContinuingChapter(User user) {
    if (!recordPresentInUCP(user)) {
      createFirstUCP(user);
    }
    Criteria criteria = HibernateUtil.getSession().createCriteria(UserChapterProgress.class)
        .add(Restrictions.eq("user", user))
        .add(Restrictions.eq("chapterRead", false))
        .addOrder(Order.asc("chapter"))
        .setMaxResults(1);
    List<UserChapterProgress> results = (List<UserChapterProgress>) criteria.list();
    if (results.size() == 0) {
      //This means that either the user is done with all the chapters or is at the finished state of a chapter
      Chapter chapter = getNextChapter(user);
      UserChapterProgress nextUCP = new UserChapterProgress(user, chapter);
      saveNextUCP(nextUCP);
      return chapter;
    }
    return (results.size() > 0) ? results.get(0).getChapter() : null;
  }

  private static void saveNextUCP(UserChapterProgress ucp) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    session.save(ucp);
    tx.commit();
    session.close();
  }

  private static Chapter getNextChapter(User user) {
    Chapter lastFinishedChapter = getLastFinishedChapter(user);
    List<Chapter> allChaptersInCourse = new ArrayList<Chapter>();
    List<Module>
        allModulesInCourse =
        getModulesForCourse(new Course(1)); //TODO - get the course number from Progress tables
    for (Module m : allModulesInCourse) {
      List<Chapter> allChaptersInModule = getChaptersForModule(m);
      allChaptersInCourse.addAll(allChaptersInModule);
    }
    Chapter[] chaptersArray = convertListToArray(allChaptersInCourse);
    int i = 0, index = 0;
    while (i < chaptersArray.length) {
      if (chaptersArray[i].getId().equals(lastFinishedChapter.getId())) {
        index = i;
        break;
      }
    }
    return allChaptersInCourse.get(index + 1);
  }

  private static Chapter[] convertListToArray(List chapterList) {
    Chapter[] chapterArray = new Chapter[chapterList.size()];
    chapterArray = (Chapter[]) chapterList.toArray(chapterArray);
    return chapterArray;
  }

  private static Chapter getLastFinishedChapter(User user) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(UserChapterProgress.class)
        .add(Restrictions.eq("user", user))
        .add(Restrictions.eq("chapterRead", true))
        .addOrder(Order.desc("chapter"))
        .setMaxResults(1);
    List<UserChapterProgress> results = (List<UserChapterProgress>) criteria.list();
    return results.get(0).getChapter();
  }

  private static void createFirstUCP(User user) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    UserChapterProgress ucp = new UserChapterProgress(user, new Chapter(1));
    session.save(ucp);
    tx.commit();
    session.close();
  }

  private static boolean recordPresentInUCP(User user) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(UserChapterProgress.class)
        .add(Restrictions.eq("user", user));
    List<UserChapterProgress> results = (List<UserChapterProgress>) criteria.list();
    return (results.size() > 0);
  }

  public static void updateUCPwithChapter(User user) {
    UserChapterProgress firstIncompleteUCP = getFirstIncompleteUCP(user);
    markUCPasChapterRead(firstIncompleteUCP);
  }

  private static void markUCPasChapterRead(UserChapterProgress firstIncompleteUCP) {
    UserChapterProgress ucpNew = null;
    if (firstIncompleteUCP != null) {
      ucpNew = new UserChapterProgress();
      ucpNew.setUser(firstIncompleteUCP.getUser());
      ucpNew.setChapter(firstIncompleteUCP.getChapter());
      ucpNew.setQuizzesDone(firstIncompleteUCP.isQuizzesDone());
      ucpNew.setChapterRead(true);
    }
    if (firstIncompleteUCP != null && ucpNew != null) {
      Session session = HibernateUtil.getSession();
      Transaction tx = session.beginTransaction();
      session.delete(firstIncompleteUCP);
      session.save(ucpNew);
      tx.commit();
      session.close();
    }
  }

  private static UserChapterProgress getFirstIncompleteUCP(User user) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(UserChapterProgress.class)
        .add(Restrictions.eq("user", user))
        .add(Restrictions.eq("chapterRead", false))
        .addOrder(Order.asc("chapter"))
        .setMaxResults(1);
    List<UserChapterProgress> results = (List<UserChapterProgress>) criteria.list();
    return (results.size() > 0) ? results.get(0) : null;
  }

  static Chapter getLastCompletedChapter(User user) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(UserChapterProgress.class)
        .add(Restrictions.eq("user",user))
        .add(Restrictions.eq("chapterRead",true))
        .addOrder(Order.desc("chapter"))
        .setMaxResults(1);
    List<UserChapterProgress> results = (List<UserChapterProgress>) criteria.list();
    return (results.size() > 0) ? results.get(0).getChapter() : null;
  }

  static String getCurrentSessionId(User user) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(SessionVariable.class)
        .add(Restrictions.eq("user",user))
        .addOrder(Order.desc("id"))
        .setMaxResults(1);
    List<SessionVariable> results = (List<SessionVariable>) criteria.list();
    return (results.size() > 0) ? results.get(0).getSessionId() : null;
  }

  static Chapter getChapterForQuiz(User user) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(UserChapterProgress.class)
        .add(Restrictions.eq("user",user))
        .addOrder(Order.desc("id"))
        .setMaxResults(1);
    List<UserChapterProgress> results = (List<UserChapterProgress>) criteria.list();
    return (results.size() > 0) ? results.get(0).getChapter() : null;
  }
}
