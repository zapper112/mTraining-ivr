package com.zapper.testIVR.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Satyarth on 25/10/16.
 */
@Entity
@Table(name = "quiz")
public class Quiz {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "chapter_id")
  private Integer chapterId;

  @Column(name = "no_of_questions")
  private Integer numberOfQuestions;

  public Quiz(Integer id, Integer chapterId, Integer numberOfQuestions) {
    this.id = id;
    this.chapterId = chapterId;
    this.numberOfQuestions = numberOfQuestions;
  }

  public Integer getNumberOfQuestions() {
    return numberOfQuestions;
  }

  public Integer getId() {
    return id;
  }

  public Integer getChapterId() {
    return chapterId;
  }

}
