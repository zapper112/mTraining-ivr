package com.zapper.testIVR.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

/**
 * Created by Satyarth on 2/11/16.
 */
@Entity
@IdClass(OptionPrimaryKey.class)
@Table(name = "choice")
public class Option implements Serializable{

  @Id
  @Column(name = "option_no")
  private Integer optionNo;

  @Id
  @Column(name = "question_id")
  private Integer questionId;

  @Column(name = "option_text")
  private String optionText;

  public Option() {}

  public Option(Integer optionNo, String optionText, Integer questionId) {
    this.optionNo = optionNo;
    this.optionText = optionText;
    this.questionId = questionId;
  }

  public Integer getOptionNo() {
    return optionNo;
  }

  public Integer getQuestionId() {
    return questionId;
  }

  public String getOptionText() {
    return optionText;
  }
}
