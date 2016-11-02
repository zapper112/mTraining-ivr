package com.zapper.testIVR.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Satyarth on 2/11/16.
 */
@Entity
@Table(name = "module")
public class Module {

  @Id
  @Column(name = "id")
  private Integer Id;

  @Column(name = "name")
  private String name;

  @Column(name = "course_id")
  private Integer courseId;

  public Module() {
  }

  public Module(Integer id, String name, Integer courseId) {

    Id = id;
    this.name = name;
    this.courseId = courseId;
  }

  public String getName() {

    return name;
  }

  public Integer getId() {
    return Id;
  }

  public Integer getCourseId() {
    return courseId;
  }
}
