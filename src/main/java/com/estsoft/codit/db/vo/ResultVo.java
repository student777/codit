package com.estsoft.codit.db.vo;


public class ResultVo {
  private int id;
  private boolean correctness;
  private int userMemory;
  private int runningTime;
  private int applicantId;
  private int testCaseId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public boolean isCorrectness() {
    return correctness;
  }

  public void setCorrectness(boolean correctness) {
    this.correctness = correctness;
  }

  public int getUserMemory() {
    return userMemory;
  }

  public void setUserMemory(int userMemory) {
    this.userMemory = userMemory;
  }

  public int getRunningTime() {
    return runningTime;
  }

  public void setRunningTime(int runningTime) {
    this.runningTime = runningTime;
  }

  public int getApplicantId() {
    return applicantId;
  }

  public void setApplicantId(int applicantId) {
    this.applicantId = applicantId;
  }

  public int getTestCaseId() {
    return testCaseId;
  }

  public void setTestCaseId(int testCaseId) {
    this.testCaseId = testCaseId;
  }

  @Override
  public String toString() {
    return "ResultVo{" +
        "id=" + id +
        ", correctness=" + correctness +
        ", userMemory=" + userMemory +
        ", runningTime=" + runningTime +
        ", applicantId=" + applicantId +
        ", testCaseId=" + testCaseId +
        '}';
  }
}
