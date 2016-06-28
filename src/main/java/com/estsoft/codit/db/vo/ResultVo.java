package com.estsoft.codit.db.vo;


public class ResultVo {
  private int id;
  private boolean correctness;
  private int user_memory;
  private int running_time;
  private int applicant_id;
  private int test_case_id;

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

  public int getUser_memory() {
    return user_memory;
  }

  public void setUser_memory(int user_memory) {
    this.user_memory = user_memory;
  }

  public int getRunning_time() {
    return running_time;
  }

  public void setRunning_time(int running_time) {
    this.running_time = running_time;
  }

  public int getApplicant_id() {
    return applicant_id;
  }

  public void setApplicant_id(int applicant_id) {
    this.applicant_id = applicant_id;
  }

  public int getTest_case_id() {
    return test_case_id;
  }

  public void setTest_case_id(int test_case_id) {
    this.test_case_id = test_case_id;
  }

  @Override
  public String toString() {
    return "ResultVo{" +
        "id=" + id +
        ", correctness=" + correctness +
        ", user_memory=" + user_memory +
        ", running_time=" + running_time +
        ", applicant_id=" + applicant_id +
        ", test_case_id=" + test_case_id +
        '}';
  }
}
