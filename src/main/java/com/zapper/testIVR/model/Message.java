package com.zapper.testIVR.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Satyarth on 2/11/16.
 */
@Entity
@Table(name = "message")
public class Message {

  private Integer id;

  private String content;

  private Chapter chapter;

  public Message() {
  }

  public Message(Integer id, String content, Chapter chapter) {
    this.id = id;
    this.content = content;
    this.chapter = chapter;
  }

  @Id
  @Column(name = "id")
  public Integer getId() {
    return id;
  }

  @Column(name = "content")
  public String getContent() {
    return content;
  }

  public Message(Integer id) {
    this.id = id;
  }

  public void setId(Integer id) {

    this.id = id;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public void setChapter(Chapter chapter) {
    this.chapter = chapter;
  }

  @ManyToOne
  @JoinColumn(name = "chapter_id")
  public Chapter getChapter() {
    return chapter;
  }
}
