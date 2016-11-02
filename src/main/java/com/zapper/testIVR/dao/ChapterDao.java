package com.zapper.testIVR.dao;

/**
 * Created by Satyarth on 2/11/16.
 */
public class ChapterDao {

  public static String getChaptersInModuleQuery() {
    return "select C.Id as id, C.name as name from Chapter C where C.moduleId = :moduleId order by C.Id";
  }

}
