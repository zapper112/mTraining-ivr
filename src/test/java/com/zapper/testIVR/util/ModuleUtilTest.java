package com.zapper.testIVR.util;

import com.zapper.testIVR.model.Module;

import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Satyarth on 2/11/16.
 */
public class ModuleUtilTest {

  @Test
  @Ignore
  public void testGetAllModulesForCourse() throws Exception {
    List<Module> modules = new ModuleUtil().getAllModulesForCourse(1);
    for(Module m : modules) {
      System.out.println("ID = " + m.getId());
      System.out.println("name = " + m.getName());
      System.out.println("CourseId = " + m.getCourseId());
    }
  }
}