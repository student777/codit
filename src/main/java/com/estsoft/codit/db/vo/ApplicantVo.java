package com.estsoft.codit.db.vo;

public class ApplicantVo {
  int id;
  String name;
  String email;
  String ticket;
  String start_time;
  String submit_time;
  int recruit_id;

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

  public String getStart_time() {
    return start_time;
  }

  public void setStart_time(String start_time) {
    this.start_time = start_time;
  }

  public String getSubmit_time() {
    return submit_time;
  }

  public void setSubmit_time(String submit_time) {
    this.submit_time = submit_time;
  }

  public int getRecruit_id() {
    return recruit_id;
  }

  public void setRecruit_id(int recruit_id) {
    this.recruit_id = recruit_id;
  }

  @Override
  public String toString() {
    return "ApplicantVo{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", email='" + email + '\'' +
        ", ticket='" + ticket + '\'' +
        ", start_time='" + start_time + '\'' +
        ", submit_time='" + submit_time + '\'' +
        ", recruit_id=" + recruit_id +
        '}';
  }
}
