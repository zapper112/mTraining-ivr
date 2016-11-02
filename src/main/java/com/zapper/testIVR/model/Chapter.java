package com.zapper.testIVR.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.swing.*;

/**
 * Created by Satyarth on 1/11/16.
 */
@Entity
@Table(name="chapter")
public class Chapter {

  @Id
  @Column(name="id")
  private Integer Id;

  @Column(name="name")
  private String name;

  @Column(name="module_id")
  private Integer moduleId;

  public Chapter() {};

  public Integer getId() {
    return this.Id;
  }

  public String getName() {
    return name;
  }

  public Chapter(Integer id, String name, Integer moduleId) {
    this.Id = id;
    this.name = name;
    this.moduleId = moduleId;
  }

  public Integer getModuleId() {
    return moduleId;
  }

}
