package com.zapper.testIVR.util;

import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.Message;
import com.zapper.testIVR.model.Module;
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
      List<Chapter> chaptersInModule = new ChapterUtil().getChaptersForModule(modules.get(i).getId());
      addChaptersInModule(response, chaptersInModule);
    }
  }

  private static void addChaptersInModule(Response response, List<Chapter> chapters) {
    for(int i = 0; i < chapters.size(); i++) {
      response.addPlayText("Chapter number - " + (i + 1));
      response.addPlayText(chapters.get(i).getName());
      List<Message> messagesForChapter = new MessageUtil().getMessagesForChapter(chapters.get(i).getId());
      List<Quiz> quizzesForChapter = new QuizUtil().getQuizzesForChapter(chapters.get(i).getId());
      ResponseUtil.addMessagesInChapter(response, messagesForChapter);
      //ResponseUtil.addQuizzesInChapter(response, quizzesForChapter);
    }
  }

  private static void addMessagesInChapter(Response response, List<Message> messages) {
    for(int i = 0; i < messages.size(); i++ )
      response.addPlayText(messages.get(i).getContent());
  }

  private static void addQuizzesInChapter(Response response, List<Quiz> quizzes) {}
 }
