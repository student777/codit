package com.estsoft.codit.db.vo;

public class ApplicantVo {
  private int id;
  private String name;
  private String email;
  private String ticket;
  private String startTime;
  private String submitTime;
  private int recruitId;

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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getTicket() {
    return ticket;
  }

  public void setTicket(String ticket) {
    this.ticket = ticket;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getSubmitTime() {
    return submitTime;
  }

  public void setSubmitTime(String submitTime) {
    this.submitTime = submitTime;
  }

  public int getRecruitId() {
    return recruitId;
  }

  public void setRecruitId(int recruitId) {
    this.recruitId = recruitId;
  }

  @Override
  public String toString() {
    return "ApplicantVo{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", ticket='" + ticket + '\'' +
        ", startTime='" + startTime + '\'' +
        ", submitTime='" + submitTime + '\'' +
        ", recruitId=" + recruitId +
        '}';
  }
}
