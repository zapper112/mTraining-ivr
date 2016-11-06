package com.zapper.testIVR.util;

import com.zapper.testIVR.dao.UserDao;
import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.Course;
import com.zapper.testIVR.model.Message;
import com.zapper.testIVR.model.Module;
import com.zapper.testIVR.model.Option;
import com.zapper.testIVR.model.Question;
import com.zapper.testIVR.model.Quiz;
import com.zapper.testIVR.model.User;
import com.zapper.testIVR.model.UserFeedback;
import com.zapper.testIVR.model.UserQuizProgress;
import com.zapper.testIVR.model.UserResponse;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

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

  static List<Module> getAllModulesForCourse(Integer courseId) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Module.class)
        .add(Restrictions.eq("courseId", courseId));
    return (List<Module>) criteria.list();
  }

  static List<Chapter> getChaptersForModule(Integer moduleId) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Chapter.class)
        .add(Restrictions.eq("moduleId", moduleId));
    return (List<Chapter>) criteria.list();
  }

  static List<Message> getMessagesForChapter(Integer chapterId) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Message.class)
        .add(Restrictions.eq("chapterId", chapterId));
    return (List<Message>) criteria.list();
  }

  static List<Quiz> getQuizzesForChapter(Integer chapterId) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Quiz.class)
        .add(Restrictions.eq("chapterId", chapterId));
    return (List<Quiz>) criteria.list();
  }

  static List<Question> getQuestionsForQuiz(Integer quizId) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Question.class);
    criteria.add(Restrictions.eq("quizId", quizId));
    return (List<Question>) criteria.list();
  }

  static List<Option> getOptionsForQuestion(Integer questionId) {
    Criteria criteria = HibernateUtil.getSession().createCriteria(Option.class)
        .add(Restrictions.eq("questionId", questionId));
    return (List<Option>) criteria.list();
  }

  static UserQuizProgress getUserQuizProgress(User user, String chapterId) {
    return new UserQuizProgress();
  }

  //TODO : delete this method when done returning UQP
  static List<Integer> getQuizAndCompletedQuestions(User user, String chapterId) {
    String quizToStartQuery = UserDao.getQuizToStartQuery();
    Query query = HibernateUtil.getSession().createQuery(quizToStartQuery).
        setString("callerId", user.getCallerId()).
        setString("chapterId", chapterId);
    List results = query.list();
    Iterator it = results.iterator();
    List<Integer> quizAndCompletedQuestions = new ArrayList<Integer>(2);
    while (it.hasNext()) {
      Object[] objects = (Object[]) it.next();
      Integer quizToStart = (Integer) objects[0];
      Integer completedQuestions = (Integer) objects[1];
      quizAndCompletedQuestions.add(quizToStart);
      quizAndCompletedQuestions.add(completedQuestions);
    }
    return quizAndCompletedQuestions;
  }

  static Question getContinuingQuestion(Integer questionId) {
    Session session = HibernateUtil.getSession();
    questionId = 2;
    Question continuingQuestion = session.get(Question.class,questionId);
    return continuingQuestion;
  }

  static Question getContinuingQuestionOld(List<Integer> quizAndCompletedQuestions) {
    String continuingQuestionQuery = UserDao.getContinuingQuestionQuery();
    Query query = HibernateUtil.getSession().createQuery(continuingQuestionQuery)
        .setParameter("quizToStart", quizAndCompletedQuestions.get(0))
        .setFirstResult(quizAndCompletedQuestions.get(1))
        .setMaxResults(1);
    List<Question> results = query.list();
    Iterator it = results.iterator();
    Question question = null;
    while (it.hasNext()) {
      Object[] objects = (Object[]) it.next();
      Integer questionId = (Integer) objects[0];
      String questionText = (String) objects[1];
      //question = new Question(questionId, quizAndCompletedQuestions.get(0), questionText);
    }
    return question;
  }

  static void saveAnswer(User user, String chapterId, String dtmf) {
    UserQuizProgress userQuizProgress = DBUtil.getUserQuizProgress(user, chapterId);
    Quiz quiz = userQuizProgress.getQuiz();
    Integer questionsAnswered = userQuizProgress.getQuestionsAnswered();
    Integer totalNumberOfQuestions = getQuestionsForQuiz(quiz.getId()).size();
    Boolean quizCompleted = (questionsAnswered + 1 == totalNumberOfQuestions);
    UserQuizProgress
        uqp =
        new UserQuizProgress(user, quiz, questionsAnswered + 1, quizCompleted);
    UserResponse
        ur =
        new UserResponse(user, new Question(), Integer.valueOf(dtmf),
            null);  //TODO : store the correct question id in user response, timestamp taken care of in database
    UserDao.saveUserFeedback(uqp);
    UserDao.saveUserFeedback(ur);
  }

}
