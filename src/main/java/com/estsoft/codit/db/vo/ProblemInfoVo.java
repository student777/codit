package com.estsoft.codit.db.vo;

public class ProblemInfoVo {
  private int id;
  private String name;
  private String description;
  private int estimated_time;

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

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public int getEstimated_time() {
    return estimated_time;
  }

  public void setEstimated_time(int estimated_time) {
    this.estimated_time = estimated_time;
  }

  @Override
  public String toString() {
    return "ProblemInfoVo{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", estimated_time=" + estimated_time +
        '}';
  }
}
