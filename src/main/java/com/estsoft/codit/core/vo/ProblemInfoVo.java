package com.estsoft.codit.core.vo;

public class ProblemInfoVo {
  int id;
  String name;
  String Text;
  int estimated_time;
  int test_time;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getText() {
    return Text;
  }

  public void setText(String text) {
    Text = text;
  }

  public int getEstimated_time() {
    return estimated_time;
  }

  public void setEstimated_time(int estimated_time) {
    this.estimated_time = estimated_time;
  }

  public int getTest_time() {
    return test_time;
  }

  public void setTest_time(int test_time) {
    this.test_time = test_time;
  }

  @Override
  public String toString() {
    return "ProblemInfoVo{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", Text='" + Text + '\'' +
        ", estimated_time=" + estimated_time +
        ", test_time=" + test_time +
        '}';
  }
}
