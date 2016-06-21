package com.estsoft.codit.db.vo;


public class CartVo {
  int id;
  int recruit_id;
  int problem_id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getRecruit_id() {
    return recruit_id;
  }

  public void setRecruit_id(int recruit_id) {
    this.recruit_id = recruit_id;
  }

  public int getProblem_id() {
    return problem_id;
  }

  public void setProblem_id(int problem_id) {
    this.problem_id = problem_id;
  }

  @Override
  public String toString() {
    return "CartVo{" +
        "id=" + id +
        ", recruit_id=" + recruit_id +
        ", problem_id=" + problem_id +
        '}';
  }
}
