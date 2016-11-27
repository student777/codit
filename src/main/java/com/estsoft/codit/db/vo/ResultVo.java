package com.estsoft.codit.db.vo;


public class ResultVo {
  private int id;
  private boolean correctness;
  private int usedMemory;
  private int runningTime;
  private int applicantId;
  private int testCaseId;
  private String created_at;

  public boolean isCorrectness() {
    return correctness;
  }

  public void setCorrectness(boolean correctness) {
    this.correctness = correctness;
  }

  public int getUsedMemory() {
    return usedMemory;
  }

  public void setUsedMemory(int usedMemory) {
    this.usedMemory = usedMemory;
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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCreated_at() {
    return created_at;
  }

  public void setCreated_at(String created_at) {
    this.created_at = created_at;
  }

  @Override
  public String toString() {
    return "ResultVo{" +
            "id=" + id +
            ", correctness=" + correctness +
            ", usedMemory=" + usedMemory +
            ", runningTime=" + runningTime +
            ", applicantId=" + applicantId +
            ", testCaseId=" + testCaseId +
            ", created_at='" + created_at + '\'' +
            '}';
  }
}
