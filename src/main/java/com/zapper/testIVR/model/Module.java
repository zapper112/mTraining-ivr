package com.zapper.testIVR.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Satyarth on 2/11/16.
 */
@Entity
@Table(name = "module")
public class Module {

  private Integer id;

  private String name;

  private Course course;

  public Module() {
  }

  public Module(Integer id, String name, Course course) {
    this.id = id;
    this.name = name;
    this.course = course;
  }

  @Column(name = "name")
  public String getName() {
    return name;
  }

  @Id
  @Column(name = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCourse(Course course) {
    this.course = course;
  }

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "course_id")
  public Course getCourse() {
    return course;
  }
}
