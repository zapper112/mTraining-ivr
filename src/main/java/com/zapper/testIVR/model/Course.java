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

  @Id
  @Column(name = "id")
  private Integer Id;

  @Column(name = "name")
  private String name;

  public Course() {
  }

  public Course(Integer id, String name) {
    this.Id = id;
    this.name = name;
  }

  public Integer getId() {

    return Id;
  }

  public void setId(Integer id) {
    Id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
