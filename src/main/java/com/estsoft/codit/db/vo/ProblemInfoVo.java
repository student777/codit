package com.estsoft.codit.db.vo;

public class ProblemInfoVo {
  private int id;
  private String name;
  private String description;
  private int estimatedTime;

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

  public int getEstimatedTime() {
    return estimatedTime;
  }

  public void setEstimatedTime(int estimatedTime) {
    this.estimatedTime = estimatedTime;
  }

  @Override
  public String toString() {
    return "ProblemInfoVo{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", description='" + description + '\'' +
        ", estimatedTime=" + estimatedTime +
        '}';
  }
}
