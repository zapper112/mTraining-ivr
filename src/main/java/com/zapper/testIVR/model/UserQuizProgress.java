package com.zapper.testIVR.model;

import com.zapper.testIVR.model.primaryKey.UserQuizProgressPrimaryKey;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Created by Satyarth on 3/11/16.
 */
@Entity
@IdClass(UserQuizProgressPrimaryKey.class)
@Table(name = "user_quiz_progress")
public class UserQuizProgress extends UserFeedback{

  @Id
  @Column(name = "user_id")
  private String callerId;

  @Id
  @Column(name = "quiz_id")
  private Integer quizId;

  @Column(name = "questions_answered")
  private Integer questionsAnswered;

  @Column(name = "quiz_completed")
  private Boolean quizCompleted;

  public String getCallerId() {
    return callerId;
  }

  public Integer getQuizId() {
    return quizId;
  }

  public Integer getQuestionsAnswered() {
    return questionsAnswered;
  }

  public Boolean getQuizCompleted() {
    return quizCompleted;
  }

  public UserQuizProgress() {
  }

  public UserQuizProgress(String callerId, Integer quizId, Integer questionsAnswered,
                          Boolean quizCompleted) {
    this.callerId = callerId;
    this.quizId = quizId;
    this.questionsAnswered = questionsAnswered;
    this.quizCompleted = quizCompleted;
  }

  public UserQuizProgress(String callerId, Integer quizId, Integer questionsAnswered) {
    this.callerId = callerId;
    this.quizId = quizId;
    this.questionsAnswered = questionsAnswered;
  }
}
