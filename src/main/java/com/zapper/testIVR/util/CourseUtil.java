package com.zapper.testIVR.util;

import com.zapper.testIVR.kookooJava.Response;
import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.Course;
import com.zapper.testIVR.model.Module;
import com.zapper.testIVR.model.User;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shankar on 1/11/16.
 */
public class CourseUtil {


  public String redirectToChapterController(User user) {
    Response response = new Response();
    response.addGotoNEXTURL("http://183.82.96.201:8100/testIVR/mtrain/chapter?cid=" + user.getCallerId());
    return response.getXML();
  }

  public Chapter getContinuingChatper(User user) {
    return DBUtil.getContinuingChapter(user);
  }
}
