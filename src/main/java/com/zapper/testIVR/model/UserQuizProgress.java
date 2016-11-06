package com.zapper.testIVR.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by Satyarth on 3/11/16.
 */
@Entity
@Table(name = "user_quiz_progress")
public class UserQuizProgress extends UserFeedback {

  private Integer id;

  private User user;

  private Quiz quiz;

  private Integer questionsAnswered;

  private Boolean quizCompleted;

  public UserQuizProgress() {
  }

  public UserQuizProgress(User user, Quiz quiz, Integer questionsAnswered) {
    this.user = user;
    this.quiz = quiz;
    this.questionsAnswered = questionsAnswered;
  }

  public UserQuizProgress(User user, Quiz quiz, Integer questionsAnswered,
                          Boolean quizCompleted) {
    this.user = user;
    this.quiz = quiz;
    this.questionsAnswered = questionsAnswered;
    this.quizCompleted = quizCompleted;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public void setQuiz(Quiz quiz) {
    this.quiz = quiz;
  }

  public void setQuestionsAnswered(Integer questionsAnswered) {
    this.questionsAnswered = questionsAnswered;
  }

  public void setQuizCompleted(Boolean quizCompleted) {
    this.quizCompleted = quizCompleted;
  }

  public void setId(Integer id) {

    this.id = id;
  }

  @Id
  @Column(name = "id")
  public Integer getId() {
    return id;
  }

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id")
  public User getUser() {
    return user;
  }

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "quiz_id")
  public Quiz getQuiz() {
    return quiz;
  }

  @Column(name = "questions_answered")
  public Integer getQuestionsAnswered() {
    return questionsAnswered;
  }

  @Column(name = "quiz_completed")
  public Boolean getQuizCompleted() {
    return quizCompleted;
  }
}
