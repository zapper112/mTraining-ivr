package com.zapper.testIVR.model;

import java.io.ObjectInput;
import java.io.Serializable;

/**
 * Created by Satyarth on 2/11/16.
 */
public class OptionPrimaryKey implements Serializable {

  private Integer optionNo;

  private Integer questionId;

  public OptionPrimaryKey() {};

  public OptionPrimaryKey(Integer optionNo, Integer questionId) {
    this.optionNo = optionNo;
    this.questionId = questionId;
  }

}
