package com.estsoft.codit.db.vo;


public class ResultVo {
  private boolean correctness;
  private int usedMemory;
  private int runningTime;
  private int applicantId;
  private int testCaseId;

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

  @Override
  public String toString() {
    return "ResultVo{" +
        "correctness=" + correctness +
        ", usedMemory=" + usedMemory +
        ", runningTime=" + runningTime +
        ", applicantId=" + applicantId +
        ", testCaseId=" + testCaseId +
        '}';
  }
}
