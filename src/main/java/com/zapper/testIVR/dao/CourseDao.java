package com.zapper.testIVR.dao;

/**
 * Created by shankar on 1/11/16.
 */
public class CourseDao {

  public static String getCourseIndexQuery() {
    return "Select C.Id as Id, C.name as name from Course C order by C.Id";
  }

}
