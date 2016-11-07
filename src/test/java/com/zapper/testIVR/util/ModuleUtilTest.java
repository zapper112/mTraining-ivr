package com.zapper.testIVR.util;

import com.zapper.testIVR.model.Course;
import com.zapper.testIVR.model.Module;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

/**
 * Created by Satyarth on 2/11/16.
 */
public class ModuleUtilTest {

  @Test
  @Ignore
  public void testGetAllModulesForCourse() throws Exception {
    List<Module> modules = new ModuleUtil().getAllModulesForCourse(new Course(1));
    for (Module m : modules) {
      System.out.println("ID = " + m.getId());
      System.out.println("name = " + m.getName());
      System.out.println("CourseId = " + m.getCourse().getId());
    }
  }

  @Test
  @Ignore
  public void shouldCreateNewCourseInDB() {
    Course testCourse = new Course(3, "hibernate basics");
    Module module = new Module(3, "hibernate module", testCourse);
    Session session = HibernateUtil.getSession();
    Transaction tx = session.beginTransaction();
    session.save(module);
    tx.commit();
    session.close();
  }
}