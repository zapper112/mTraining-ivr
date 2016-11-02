package com.zapper.testIVR.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Satyarth on 2/11/16.
 */
@Entity
@Table(name = "message")
public class Message {

  @Id
  @Column(name = "id")
  private Integer Id;

  @Column(name = "content")
  private String content;

  @Column(name = "chapter_id")
  private Integer chapterId;

  public Message() {
  }

  public Message(Integer id, String content, Integer chapterId) {
    this.Id = id;
    this.chapterId = chapterId;
    this.content = content;
  }

  public Integer getChapterId() {

    return chapterId;
  }

  public String getContent() {
    return content;
  }

  public Integer getId() {
    return Id;
  }
}
