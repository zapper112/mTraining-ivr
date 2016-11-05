package com.zapper.testIVR.model.primaryKey;

import java.io.Serializable;

import javax.persistence.IdClass;

/**
 * Created by Satyarth on 3/11/16.
 */

public class UserQuizProgressPrimaryKey implements Serializable {

  private String callerId;
  private Integer quizId;

  public UserQuizProgressPrimaryKey() {
  }

  public UserQuizProgressPrimaryKey(String callerId, Integer quizId) {
    this.callerId = callerId;
    this.quizId = quizId;
  }
}
