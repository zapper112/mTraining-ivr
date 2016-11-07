package com.zapper.testIVR.util;

import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.Module;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Satyarth on 2/11/16.
 */
public class ChapterUtilTest {

  @Test
  @Ignore
  public void testGetChaptersForModule() throws Exception {
    List<Chapter> chapters = new ChapterUtil().getChaptersForModule(new Module(1));
    for(Chapter chapter : chapters) {
      System.out.println("id = " + chapter.getId());
      System.out.println("name = " + chapter.getName());
      System.out.println("moduleId = " + chapter.getModule().getId());
    }
  }
}