package com.zapper.testIVR.model;

import com.zapper.testIVR.model.primaryKey.OptionPrimaryKey;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
  @ManyToOne
  @JoinColumn(name = "question_id")
  private Question question;

  @Column(name = "option_text")
  private String optionText;

  public Option() {}

  public Option(Integer optionNo, String optionText, Question question) {
    this.optionNo = optionNo;
    this.optionText = optionText;
    this.question = question;
  }

  public Integer getOptionNo() {
    return optionNo;
  }

  public void setOptionNo(Integer optionNo) {
    this.optionNo = optionNo;
  }

  public void setQuestion(Question question) {
    this.question = question;
  }

  public void setOptionText(String optionText) {
    this.optionText = optionText;
  }

  public Question getQuestionId() {
    return question;
  }

  public String getOptionText() {
    return optionText;
  }
}
