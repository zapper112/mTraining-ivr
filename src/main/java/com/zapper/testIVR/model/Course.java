package com.zapper.testIVR.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Satyarth on 1/11/16.
 */
@Entity
@Table(name = "course")
public class Course {

  private Integer id;

  private String name;

  public Course() {
  }

  public Course(Integer id, String name) {
    this.id = id;
    this.name = name;
  }

  @Id
  @Column(name = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @Column(name = "name")
  public String getName() {
    return name;
  }

  public Course(Integer id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }
}
