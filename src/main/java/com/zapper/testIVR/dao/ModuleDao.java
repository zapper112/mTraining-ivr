package com.zapper.testIVR.dao;

import com.zapper.testIVR.util.HibernateUtil;

import org.hibernate.Query;

/**
 * Created by Satyarth on 2/11/16.
 */
public class ModuleDao {

  public static String getModulesInCourseQuery(Integer courseId) {
    return  "select M.id as id, M.name as name from Module M where M.courseId = :courseId order by M.id";
  }
}
