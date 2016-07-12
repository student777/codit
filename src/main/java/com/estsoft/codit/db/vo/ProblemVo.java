package com.estsoft.codit.db.vo;

public class ProblemVo {
  private int id;
  private int languageId;
  private int problemInfoId;
  private String skeletonCode;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getLanguageId() {
    return languageId;
  }

  public void setLanguageId(int languageId) {
    this.languageId = languageId;
  }

  public int getProblemInfoId() {
    return problemInfoId;
  }

  public void setProblemInfoId(int problemInfoId) {
    this.problemInfoId = problemInfoId;
  }

  public String getSkeletonCode() {
    return skeletonCode;
  }

  public void setSkeletonCode(String skeletonCode) {
    this.skeletonCode = skeletonCode;
  }

  @Override
  public String toString() {
    return "ProblemVo{" +
        "id=" + id +
        ", languageId=" + languageId +
        ", problemInfoId=" + problemInfoId +
        ", skeletonCode='" + skeletonCode + '\'' +
        '}';
  }
}
