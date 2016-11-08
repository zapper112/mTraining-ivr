package com.zapper.testIVR.model;

import java.io.Serializable;
import java.security.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Created by Satyarth on 7/11/16.
 */
@Entity
@Table(name = "sessionVariable")
public class SessionVariable implements Serializable{

  private Integer id;
  private User user;
  private String sessionId;
  private Chapter chapter;

  public SessionVariable() {
  }

  public SessionVariable(User user, String sessionId) {
    this.user = user;
    this.sessionId = sessionId;
  }

  public SessionVariable(User user, String sessionId, Chapter chapter) {
    this.user = user;
    this.sessionId = sessionId;
    this.chapter = chapter;
  }

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  @ManyToOne
  @JoinColumn(name = "callerId")
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Column(name = "sessionId")
  public String getSessionId() {
    return sessionId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  @ManyToOne
  @JoinColumn(name = "chapterId")
  public Chapter getChapter() {
    return chapter;
  }

  public void setChapter(Chapter chapter) {
    this.chapter = chapter;
  }

}