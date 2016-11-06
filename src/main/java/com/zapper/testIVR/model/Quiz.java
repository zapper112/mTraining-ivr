package com.zapper.testIVR.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Satyarth on 25/10/16.
 */
@Entity
@Table(name = "quiz")
public class Quiz {

  private Integer id;

  private Chapter chapter;

  private Integer numberOfQuestions;

  public Quiz() {
  }

  public Quiz(Integer id, Chapter chapter, Integer numberOfQuestions) {
    this.id = id;
    this.chapter = chapter;
    this.numberOfQuestions = numberOfQuestions;
  }

  @Id
  @Column(name = "id")
  public Integer getId() {
    return id;
  }


  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "chapter_id")
  public Chapter getChapter() {
    return chapter;
  }

  public void setChapter(Chapter chapter) {
    this.chapter = chapter;
  }

  public void setNumberOfQuestions(Integer numberOfQuestions) {
    this.numberOfQuestions = numberOfQuestions;
  }

  public void setId(Integer id) {

    this.id = id;
  }

  @Column(name = "no_of_questions")

  public Integer getNumberOfQuestions() {
    return numberOfQuestions;
  }
}
