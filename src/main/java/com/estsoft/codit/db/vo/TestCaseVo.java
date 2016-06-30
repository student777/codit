package com.estsoft.codit.db.vo;

public class TestCaseVo {
  private int id;
  private String input;
  private int problemInfoId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getInput() {
    return input;
  }

  public void setInput(String input) {
    this.input = input;
  }

  public int getProblemInfoId() {
    return problemInfoId;
  }

  public void setProblemInfoId(int problemInfoId) {
    this.problemInfoId = problemInfoId;
  }

  @Override
  public String toString() {
    return "TestCaseVo{" +
        "id=" + id +
        ", input='" + input + '\'' +
        ", problemInfoId=" + problemInfoId +
        '}';
  }
}
