package com.estsoft.codit.db.vo;


public class CartVo {
  private int recruitId;
  private int problemInfoId;

  public int getRecruitId() {
    return recruitId;
  }

  public void setRecruitId(int recruitId) {
    this.recruitId = recruitId;
  }

  public int getProblemInfoId() {
    return problemInfoId;
  }

  public void setProblemInfoId(int problemInfoId) {
    this.problemInfoId = problemInfoId;
  }

  @Override
  public String toString() {
    return "CartVo{" +
        "recruitId=" + recruitId +
        ", problemInfoId=" + problemInfoId +
        '}';
  }
}
