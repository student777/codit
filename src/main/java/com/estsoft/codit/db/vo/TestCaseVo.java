package com.estsoft.codit.db.vo;

public class TestCaseVo {
  private int id;
  private String input;
  private int problem_info_id;

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

  public int getProblem_info_id() {
    return problem_info_id;
  }

  public void setProblem_info_id(int problem_info_id) {
    this.problem_info_id = problem_info_id;
  }

  @Override
  public String toString() {
    return "TestCaseVo{" +
        "id=" + id +
        ", input='" + input + '\'' +
        ", problem_info_id=" + problem_info_id +
        '}';
  }
}
