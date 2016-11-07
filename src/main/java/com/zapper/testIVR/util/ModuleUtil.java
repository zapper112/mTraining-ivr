package com.zapper.testIVR.util;

import com.zapper.testIVR.model.Course;
import com.zapper.testIVR.model.Module;

import java.util.List;

/**
 * Created by Satyarth on 2/11/16.
 */
public class ModuleUtil {

  public List<Module> getAllModulesForCourse(Course course) {
    return DBUtil.getAllModulesForCourse(course);
  }
}
