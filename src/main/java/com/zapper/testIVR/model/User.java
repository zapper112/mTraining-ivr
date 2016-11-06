package com.zapper.testIVR.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Satyarth on 26/10/16.
 */
@Entity
@Table(name = "user")
public class User {

  private String callerId;

  private String sessionId;

  public User() {
  }

  public User(String callerId, String sessionId) {
    setCallerId(callerId);
    setSessionId(sessionId);
  }

  public void setCallerId (String callerId) {
    this.callerId = callerId;
  }

  @Id
  @Column(name = "callerId")
  public String getCallerId() {
    return this.callerId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

  @Column(name = "sessionId")
  public String getSessionId () {
    return this.sessionId;
  }
}
