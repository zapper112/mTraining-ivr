package com.zapper.testIVR.util;

import com.zapper.testIVR.kookooJava.CollectDtmf;
import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.Message;
import com.zapper.testIVR.model.Module;
import com.zapper.testIVR.model.Option;
import com.zapper.testIVR.model.Question;
import com.zapper.testIVR.model.Quiz;

import java.util.List;

/**
 * Created by Satyarth on 2/11/16.
 */
public class ResponseUtil {

  public static void addModulesInChapter(Response response, List<Module> modules) {
    for(int i = 0 ; i < modules.size(); i++) {
      response.addPlayText("Module number - " + (i + 1));
      response.addPlayText(modules.get(i).getName());
      List<Chapter> chaptersInModule = new ChapterUtil().getChaptersForModule(modules.get(i));
      addChaptersInModule(response, chaptersInModule);
    }
  }

  private static void addChaptersInModule(Response response, List<Chapter> chapters) {
    for(int i = 0; i < chapters.size(); i++) {
      response.addPlayText("Chapter number - " + (i + 1));
      response.addPlayText(chapters.get(i).getName());
      List<Message> messagesForChapter = new MessageUtil().getMessagesForChapter(chapters.get(i));
      List<Quiz> quizzesForChapter = new QuizUtil().getQuizzesForChapter(chapters.get(i));
      ResponseUtil.addMessagesInChapter(response, messagesForChapter);
      ResponseUtil.addQuizzesInChapter(response, quizzesForChapter);
    }
  }

  private static void addMessagesInChapter(Response response, List<Message> messages) {
      for(Message message : messages)
      response.addPlayText(message.getContent());
  }

  private static void addQuizzesInChapter(Response response, List<Quiz> quizzes) {
    for(Quiz quiz : quizzes)
      ResponseUtil.addQuizInChapter(response, quiz);
  }

  private static void addQuizInChapter(Response response, Quiz quiz) {
    List<Question> questions = new QuizUtil().getQuestionsForQuiz(quiz);
    for(Question question : questions) addQuestionInQuiz(response, question);
  }

  private static void addQuestionInQuiz(Response response, Question question) {
    List<Option> options = new QuizUtil().getOptionsForQuestion(question);
    CollectDtmf cd = new CollectDtmf();
    cd.addPlayText(question.getQuestionText());
    for(Option option : options) {
      cd.addPlayText("Option number - " + option.getOptionNo());
      cd.addPlayText(option.getOptionText());
    }
    response.addCollectDtmf(cd);
  }
 }
