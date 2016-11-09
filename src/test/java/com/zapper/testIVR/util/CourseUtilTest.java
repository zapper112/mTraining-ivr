package com.zapper.testIVR.util;

import com.zapper.testIVR.model.User;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Satyarth on 1/11/16.
 */
public class CourseUtilTest {

  @Test
  @Ignore
  public void testStartCoursesFromBeginning() {
    System.out.println(new CourseUtil().startCoursesFromBeginning());
  }

  @Test
  @Ignore
  public void testChapterRedirect() {
    System.out.println(new CourseUtil().redirectToChapterController(new User("90")));
  }

}