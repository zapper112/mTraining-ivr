package com.zapper.testIVR.model;

import java.security.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Satyarth on 4/11/16.
 */
@Entity
@Table(name = "user_response")
public class UserResponse extends UserFeedback {

  private Integer id;

  private User user;

  private Question question;

  private Integer optionNo;

  private Timestamp timeOfAnswer;

  public UserResponse() {
  }

  public UserResponse(User user, Question question, Integer optionNo,
                      Timestamp timeOfAnswer) {
    this.user = user;
    this.question = question;
    this.optionNo = optionNo;
    this.timeOfAnswer = timeOfAnswer;
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
  @JoinColumn(name = "questionId")
  public Question getQuestion() {
    return question;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  @Column(name = "optionNo")
  public Integer getOptionNo() {
    return optionNo;
  }

  public void setOptionNo(Integer optionNo) {
    this.optionNo = optionNo;
  }

  @Column(name = "timeOfAnswer")
  public Timestamp getTimeOfAnswer() {
    return timeOfAnswer;
  }

  public void setTimeOfAnswer(Timestamp timeOfAnswer) {
    this.timeOfAnswer = timeOfAnswer;
  }
}
