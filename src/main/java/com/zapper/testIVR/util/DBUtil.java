package com.zapper.testIVR.util;

import com.zapper.testIVR.dao.ChapterDao;
import com.zapper.testIVR.dao.CourseDao;
import com.zapper.testIVR.dao.MessageDao;
import com.zapper.testIVR.dao.ModuleDao;
import com.zapper.testIVR.model.Chapter;
import com.zapper.testIVR.model.Course;
import com.zapper.testIVR.model.Message;
import com.zapper.testIVR.model.Module;

import org.hibernate.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by shankar on 1/11/16.
 */
public class DBUtil {

  static List<Course> getCourseIndex() {
    String hibernateQuery = CourseDao.getCourseIndexQuery();
    Query query = HibernateUtil.getSession().createQuery(hibernateQuery);
    List<Course> results = query.list();
    Iterator it = results.iterator();
    List<Course> courses = new ArrayList<Course>();
    while(it.hasNext()) {
      Object[] objects = (Object[]) it.next();
      Integer courseId =(Integer) objects[0];
      String courseName = (String) objects[1];
      courses.add(new Course(courseId, courseName));
    }
    return courses;
  }

  static List<Module> getAllModulesForCourse(Integer courseId) {
    String modulesQuery = ModuleDao.getModulesInCourseQuery(courseId);
    Query query = HibernateUtil.getSession().createQuery(modulesQuery).setParameter("courseId",courseId);
    List<Module> results = query.list();
    Iterator it = results.iterator();
    List<Module> modules = new ArrayList<Module>();
    while(it.hasNext()) {
      Object[] objects = (Object[]) it.next();
      Integer moduleId = (Integer) objects[0];
      String moduleName = (String) objects[1];
      modules.add(new Module(moduleId,moduleName,courseId));
    }
    return modules;
  }

  static List<Chapter> getChaptersForModule(Integer moduleId) {
    String chapterQuery = ChapterDao.getChaptersInModuleQuery(moduleId);
    Query query = HibernateUtil.getSession().createQuery(chapterQuery).setParameter("moduleId",moduleId);
    List<Chapter> results = query.list();
    Iterator it = results.iterator();
    List<Chapter> chapters = new ArrayList<Chapter>();
    while(it.hasNext()) {
      Object[] objects = (Object[]) it.next();
      Integer chatperId = (Integer) objects[0];
      String chapterName = (String) objects[1];
      chapters.add(new Chapter(chatperId, chapterName, moduleId));
    }
    return chapters;
  }

  static List<Message> getMessagesForChapter(Integer chapterId) {
    String messageQuery = MessageDao.getMessagesInChapterQuery(chapterId);
    Query query = HibernateUtil.getSession().createQuery(messageQuery).setParameter("chapterId",chapterId);
    List<Message> results = query.list();
    Iterator it = results.iterator();
    List<Message> messages = new ArrayList<Message>();
    while(it.hasNext()) {
      Object[] objects = (Object[]) it.next();
      Integer messageId = (Integer) objects[0];
      String messageContent = (String) objects[1];
      messages.add(new Message(messageId,messageContent,chapterId));
    }
    return messages;
  }

}
