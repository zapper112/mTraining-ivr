package com.zapper.testIVR.model;

import com.zapper.testIVR.model.primaryKey.UserResponsePrimaryKey;

import java.security.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Created by Satyarth on 4/11/16.
 */
@Entity
@IdClass(UserResponsePrimaryKey.class)
@Table(name = "user_response")
public class UserResponse extends UserFeedback{

  @Id
  @Column(name = "caller_id")
  private String callerId;

  @Id
  @Column(name = "question_id")
  private Integer questionId;

  @Column(name = "option_no")
  private Integer optionNo;

  @Column(name = "time_of_answer")
  private Timestamp timeOfAnswer;

  public String getCallerId() {
    return callerId;
  }

  public Integer getQuestionId() {
    return questionId;
  }

  public Integer getOptionNo() {
    return optionNo;
  }

  public Timestamp getTimeOfAnswer() {
    return timeOfAnswer;
  }

  public UserResponse() {

  }

  public UserResponse(String callerId, Integer questionId, Integer optionNo,
                      Timestamp timeOfAnswer) {

    this.callerId = callerId;
    this.questionId = questionId;
    this.optionNo = optionNo;
    this.timeOfAnswer = timeOfAnswer;
  }
}
