package com.zapper.testIVR.util;

import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.Module;

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
    }
  }
}
