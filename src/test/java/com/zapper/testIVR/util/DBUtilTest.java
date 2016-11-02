package com.zapper.testIVR.util;

import com.zapper.testIVR.model.Course;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Satyarth on 1/11/16.
 */
public class DBUtilTest {

  private DBUtil dbUtil = new DBUtil();

  @Test
  @Ignore
  public void getCourseIndexTest() {
   List<Course> courses = dbUtil.getCourseIndex();
    for(Course c : courses) {
      System.out.println("Id = " + c.getId());
      System.out.println("name = " + c.getName());
    }
  }

}