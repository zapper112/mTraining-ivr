package com.zapper.testIVR.model.primaryKey;

import com.zapper.testIVR.model.Question;
import com.zapper.testIVR.model.Quiz;

import java.io.ObjectInput;
import java.io.Serializable;

/**
 * Created by Satyarth on 2/11/16.
 */
public class OptionPrimaryKey implements Serializable {

  private Integer optionNo;

  private Question question;

  public OptionPrimaryKey() {};

  public OptionPrimaryKey(Integer optionNo, Question question) {
    this.optionNo = optionNo;
    this.question = question;
  }
}
