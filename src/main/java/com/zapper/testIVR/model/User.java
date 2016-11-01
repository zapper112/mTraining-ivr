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

  @Id
  @Column(name = "callerId")
  private String callerId;

  @Column(name = "sessionId")
  private String sessionId;

  public void setCallerId (String callerId) {
    this.callerId = callerId;
  }

  public String getCallerId() {
    return this.callerId;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }
  
  public String getSessionId () {
    return this.sessionId;
  }
}
