package com.zapper.testIVR.model;

import org.hibernate.annotations.Cache;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Satyarth on 25/10/16.
 */
@Entity
@Table(name = "question")
public class Question {

  @Id
  @Column(name = "id")
  private Integer id;

  @Column(name = "quiz_id")
  private Integer quizId;

  @Column(name = "question_text")
  private String questionText;

  @Column(name = "correct_option_no")
  private Integer correctOptionNo;

  public Question(Integer id, Integer quizId, String questionText, Integer correctOptionNo) {
    this.id = id;
    this.quizId = quizId;
    this.questionText = questionText;
    this.correctOptionNo = correctOptionNo;
  }

  public Integer getId() {

    return id;
  }

  public Integer getQuizId() {
    return quizId;
  }

  public String getQuestionText() {
    return questionText;
  }

  public Integer getCorrectOptionNo() {
    return correctOptionNo;
  }
}
