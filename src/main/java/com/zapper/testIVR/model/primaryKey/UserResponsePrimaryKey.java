package com.zapper.testIVR.model.primaryKey;

import java.io.Serializable;

/**
 * Created by Satyarth on 4/11/16.
 */
public class UserResponsePrimaryKey implements Serializable{

  private String callerId;

  private Integer questionId;

  public String getCallerId() {
    return callerId;
  }

  public Integer getQuestionId() {
    return questionId;
  }

  public UserResponsePrimaryKey() {
  }

  public UserResponsePrimaryKey(String callerId, Integer questionId) {
    this.callerId = callerId;
    this.questionId = questionId;
  }
}
