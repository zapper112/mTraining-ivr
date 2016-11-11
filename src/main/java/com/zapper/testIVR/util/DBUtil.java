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
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shankar on 1/11/16.
 */
public class DBUtil {

  static List<Module> getModulesForCourse(Course course) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(Module.class)
        .add(Restrictions.eq("course", course))
        .addOrder(Order.asc("id"));
    List<Module> modules = (List<Module>) criteria.list();
    tx.commit();
    session.close();
    return modules;
  }

  static List<Chapter> getChaptersForModule(Module module) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(Chapter.class)
        .add(Restrictions.eq("module", module))
        .addOrder(Order.asc("id"));
    List<Chapter> chapters = (List<Chapter>) criteria.list();
    tx.commit();
    session.close();
    return chapters;
  }

  static List<Message> getMessagesForChapter(Chapter chapter) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(Message.class)
        .add(Restrictions.eq("chapter", chapter))
        .addOrder(Order.asc("id"));
    List<Message> messages = (List<Message>) criteria.list();
    tx.commit();
    session.close();
    return messages;
  }

  static List<Quiz> getQuizzesForChapter(Chapter chapter) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(Quiz.class)
        .add(Restrictions.eq("chapter", chapter))
        .addOrder(Order.asc("id"));
    List<Quiz> quizzes = (ArrayList<Quiz>) criteria.list();
    tx.commit();
    session.close();
    return quizzes;
  }

  static List<Question> getQuestionsForQuiz(Quiz quiz) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(Question.class)
        .add(Restrictions.eq("quiz", quiz))
        .addOrder(Order.asc("id"));
    List<Question> questions = (List<Question>) criteria.list();
    tx.commit();
    session.close();
    return questions;
  }

  static List<Option> getOptionsForQuestion(Question question) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(Option.class)
        .add(Restrictions.eq("question", question))
        .addOrder(Order.asc("optionNo"));
    List<Option> options = (List<Option>) criteria.list();
    tx.commit();
    session.close();
    return options;
  }

  static UserQuizProgress getUserQuizProgress(User user, Chapter chapter) {
    if (!recordPresentInUQP(user, chapter)) {
      createNewUQPs(user, chapter);
    }
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(UserQuizProgress.class, "uqp")
        .add(Restrictions.eq("user", user))
        .add(Restrictions.eq("quizCompleted", false));
    criteria.createAlias("quiz", "uq")
        .add(Restrictions.eq("uq.chapter", chapter))
        .addOrder(Order.asc("uqp.quiz"))
        .setMaxResults(1);
    List<UserQuizProgress> results = (List<UserQuizProgress>) criteria.list();
    tx.commit();
    session.close();
    if (results.size() == 0) {
      //This means that either the user has already completed all the quizzes or is currently at finished state of a quiz
      UserQuizProgress nextUQP = getNextUserQuizProgress(user, chapter);
      saveNextUQP(nextUQP);
      return null;
    }
    return results.get(0);
  }

  private static UserQuizProgress getNextUserQuizProgress(User user, Chapter chapter) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(Quiz.class)
        .add(Restrictions.gt("chapter",chapter))
        .addOrder(Order.asc("id"))
        .setMaxResults(1);
    List<Quiz> results = (List<Quiz>) criteria.list();
    tx.commit();
    session.close();
    Quiz nextQuiz = (results.size() > 0) ? results.get(0) : null;
    UserQuizProgress nextUQP = new UserQuizProgress(user, nextQuiz, 0, false);
    return nextUQP;
  }

  private static void saveNextUQP(UserQuizProgress nextUQP) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    session.save(nextUQP);
    tx.commit();
    session.close();
  }

  static Question getContinuingQuestion(UserQuizProgress uqp) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(Question.class)
        .add(Restrictions.eq("quiz", uqp.getQuiz()))
        .addOrder(Order.asc("id"))
        .setFirstResult(uqp.getQuestionsAnswered())
        .setMaxResults(1);
    List<Question> results = (List<Question>) criteria.list();
    tx.commit();
    session.close();
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
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(UserQuizProgress.class)
        .add(Restrictions.eq("user", user))
        .createAlias("quiz", "q")
        .add(Restrictions.eq("q.chapter", chapter));
    List results = criteria.list();
    tx.commit();
    session.close();
    return (results.size() >= 1);
  }

  //this method will add UQPs for all quizzes in the chapter
  private static void createNewUQPs(User user, Chapter chapter) {
    List<Quiz> quizzesInChapter = getQuizzesForChapter(chapter);
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    for(Quiz quiz : quizzesInChapter) {
      UserQuizProgress uqp = new UserQuizProgress(user, quiz, 0, false);
      session.save(uqp);
    }
    tx.commit();
    session.close();
  }

  
  static void saveUserSession(SessionVariable sv) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    session.save(sv);
    tx.commit();
    session.close();
  }

  static SessionVariable getSessionVariable(User user, String sessionId) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(SessionVariable.class)
        .add(Restrictions.eq("user", user))
        .add(Restrictions.eq("sessionId", sessionId));
    List<SessionVariable> results = (List<SessionVariable>) criteria.list();
    tx.commit();
    session.close();
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

  static Chapter getContinuingChapter(User user) {
    if (!recordPresentInUCP(user)) {
      createFirstUCP(user);
    }
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(UserChapterProgress.class)
        .add(Restrictions.eq("user", user))
        .add(Restrictions.eq("chapterRead", false))
        .addOrder(Order.asc("chapter"))
        .setMaxResults(1);
    List<UserChapterProgress> results = (List<UserChapterProgress>) criteria.list();
    tx.commit();
    session.close();
    if (results.size() == 0) {
      //This means that either the user is done with all the chapters or is at the finished state of a chapter
      Chapter chapter = getNextChapter(user);
      if(chapter.getId() == -1) {
        return chapter;
      }
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
    //returning chapter with id = -1 in case all the chapters in the course are finished
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
      i++;
    }
    return (index < chaptersArray.length -1 )? allChaptersInCourse.get(index + 1) : new Chapter(-1);
  }

  private static Chapter[] convertListToArray(List chapterList) {
    Chapter[] chapterArray = new Chapter[chapterList.size()];
    chapterArray = (Chapter[]) chapterList.toArray(chapterArray);
    return chapterArray;
  }

  private static Chapter getLastFinishedChapter(User user) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(UserChapterProgress.class)
        .add(Restrictions.eq("user", user))
        .add(Restrictions.eq("chapterRead", true))
        .addOrder(Order.desc("chapter"))
        .setMaxResults(1);
    List<UserChapterProgress> results = (List<UserChapterProgress>) criteria.list();
    tx.commit();
    session.close();
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
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(UserChapterProgress.class)
        .add(Restrictions.eq("user", user));
    List<UserChapterProgress> results = (List<UserChapterProgress>) criteria.list();
    tx.commit();
    session.close();
    return (results.size() > 0);
  }

  static void updateUCPwithChapter(User user) {
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
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(UserChapterProgress.class)
        .add(Restrictions.eq("user", user))
        .add(Restrictions.eq("chapterRead", false))
        .addOrder(Order.asc("chapter"))
        .setMaxResults(1);
    List<UserChapterProgress> results = (List<UserChapterProgress>) criteria.list();
    tx.commit();
    session.close();
    return (results.size() > 0) ? results.get(0) : null;
  }

  static Chapter getLastCompletedChapter(User user) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(UserChapterProgress.class)
        .add(Restrictions.eq("user",user))
        .add(Restrictions.eq("chapterRead",true))
        .addOrder(Order.desc("chapter"))
        .setMaxResults(1);
    List<UserChapterProgress> results = (List<UserChapterProgress>) criteria.list();
    tx.commit();
    session.close();
    return (results.size() > 0) ? results.get(0).getChapter() : null;
  }

  static String getCurrentSessionId(User user) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(SessionVariable.class)
        .add(Restrictions.eq("user",user))
        .addOrder(Order.desc("id"))
        .setMaxResults(1);
    List<SessionVariable> results = (List<SessionVariable>) criteria.list();
    tx.commit();
    session.close();
    return (results.size() > 0) ? results.get(0).getSessionId() : null;
  }

  static Chapter getChapterForQuiz(User user) {
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(UserChapterProgress.class)
        .add(Restrictions.eq("user",user))
        .addOrder(Order.desc("id"))
        .setMaxResults(1);
    List<UserChapterProgress> results = (List<UserChapterProgress>) criteria.list();
    tx.commit();
    session.close();
    return (results.size() > 0) ? results.get(0).getChapter() : null;
  }
}
