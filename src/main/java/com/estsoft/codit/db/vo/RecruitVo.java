package com.estsoft.codit.db.vo;

public class RecruitVo {
  private int id;
  private String title;
  private String from_date;
  private String to_date;
  private int client_id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getFrom_date() {
    return from_date;
  }

  public void setFrom_date(String from_date) {
    this.from_date = from_date;
  }

  public String getTo_date() {
    return to_date;
  }

  public void setTo_date(String to_date) {
    this.to_date = to_date;
  }

  public int getClient_id() {
    return client_id;
  }

  public void setClient_id(int client_id) {
    this.client_id = client_id;
  }

  @Override
  public String toString() {
    return "RecruitVo{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", from_date='" + from_date + '\'' +
        ", to_date='" + to_date + '\'' +
        ", client_id=" + client_id +
        '}';
  }
}
