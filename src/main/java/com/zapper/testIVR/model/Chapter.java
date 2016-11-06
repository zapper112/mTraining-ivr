package com.zapper.testIVR.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.swing.*;

/**
 * Created by Satyarth on 1/11/16.
 */
@Entity
@Table(name="chapter")
public class Chapter {

  private Integer id;

  private String name;

  private Module module;

  public Chapter() {};

  public Chapter(Integer id, String name, Module module) {
    this.id = id;
    this.name = name;
    this.module = module;
  }

  @Id
  @Column(name="id")
  public Integer getId() {
    return id;
  }

  @Column(name="name")
  public String getName() {
    return name;
  }

  public void setModule(Module module) {
    this.module = module;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name="module_id")
  public Module getModule() {
    return module;
  }

}
