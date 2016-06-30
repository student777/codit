package com.estsoft.codit.db.vo;


public class CartVo {
  private int id;
  private int recruitId;
  private int problemId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getRecruitId() {
    return recruitId;
  }

  public void setRecruitId(int recruitId) {
    this.recruitId = recruitId;
  }

  public int getProblemId() {
    return problemId;
  }

  public void setProblemId(int problemId) {
    this.problemId = problemId;
  }

  @Override
  public String toString() {
    return "CartVo{" +
        "id=" + id +
        ", recruitId=" + recruitId +
        ", problemId=" + problemId +
        '}';
  }
}
