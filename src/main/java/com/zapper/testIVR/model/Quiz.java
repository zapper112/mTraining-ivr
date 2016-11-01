package com.zapper.testIVR.model;

import java.util.List;

/**
 * Created by Satyarth on 25/10/16.
 */
public class Quiz {

  public static final String instructions = "Enter the number against the correct choice. Press # key to exit.";
  private List<Question> questions;

  public List<Question> getQuestions() {
    return this.questions;
  }
}
