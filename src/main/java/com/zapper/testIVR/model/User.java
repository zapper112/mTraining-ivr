package com.zapper.testIVR.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Satyarth on 26/10/16.
 */
@Entity
@Table(name = "user")
public class User implements Serializable{

  private String callerId;

  public void setCallerId (String callerId) {
    this.callerId = callerId;
  }

  @Id
  @Column(name = "callerId")
  public String getCallerId() {
    return this.callerId;
  }

  public User() {
  }

  public User(String callerId) {
    this.callerId = callerId;
  }

}
