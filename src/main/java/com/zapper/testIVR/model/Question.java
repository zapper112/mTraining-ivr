package com.zapper.testIVR.model;

import org.hibernate.annotations.Cache;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

  @ManyToOne
  @JoinColumn(name = "quiz_id")
  private Quiz quiz;

  @Column(name = "question_text")
  private String questionText;

  @Column(name = "correct_option_no")
  private Integer correctOptionNo;

  public Question(Integer id) {
    this.id = id;
  }

  public Question() {
  }

  public Question(Integer id, Quiz quiz, String questionText, Integer correctOptionNo) {

    this.id = id;
    this.quiz = quiz;
    this.questionText = questionText;
    this.correctOptionNo = correctOptionNo;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Integer getId() {
    return id;
  }

  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
  }

  public void setQuestionText(String questionText) {
    this.questionText = questionText;
  }

  public void setCorrectOptionNo(Integer correctOptionNo) {
    this.correctOptionNo = correctOptionNo;
  }

  public Quiz getQuiz() {
    return quiz;
  }

  public String getQuestionText() {
    return questionText;
  }

  public Integer getCorrectOptionNo() {
    return correctOptionNo;
  }
}
