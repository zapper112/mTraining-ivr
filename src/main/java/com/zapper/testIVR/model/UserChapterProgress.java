package com.zapper.testIVR.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Satyarth on 8/11/16.
 */
@Entity
@Table(name = "userChapterProgress")
public class UserChapterProgress {

  private Integer id;
  private User user;
  private Chapter chapter;
  private boolean chapterRead;
  private boolean quizzesDone;


  public UserChapterProgress() {
  }

  public UserChapterProgress(User user, Chapter chapter) {
    this.user = user;
    this.chapter = chapter;
  }

  public UserChapterProgress(User user, Chapter chapter, boolean chapterRead) {
    this.user = user;
    this.chapter = chapter;
    this.chapterRead = chapterRead;
  }

  public UserChapterProgress(User user, Chapter chapter, boolean chapterRead, boolean quizzesDone) {
    this.user = user;
    this.chapter = chapter;
    this.chapterRead = chapterRead;
    this.quizzesDone = quizzesDone;
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

  @ManyToOne
  @JoinColumn(name = "chapterId")
  public Chapter getChapter() {
    return chapter;
  }

  public void setChapter(Chapter chapter) {
    this.chapter = chapter;
  }

  @Column(name = "chapterRead")
  public boolean isChapterRead() {
    return chapterRead;
  }

  public void setChapterRead(boolean chapterRead) {
    this.chapterRead = chapterRead;
  }

  @Column(name = "quizzesDone")
  public boolean isQuizzesDone() {
    return quizzesDone;
  }

  public void setQuizzesDone(boolean quizzesDone) {
    this.quizzesDone = quizzesDone;
  }
}
