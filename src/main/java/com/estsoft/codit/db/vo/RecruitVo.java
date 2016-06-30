package com.estsoft.codit.db.vo;

public class RecruitVo {
  private int id;
  private String title;
  private String fromDate;
  private String toDate;
  private int clientId;

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

  public String getFromDate() {
    return fromDate;
  }

  public void setFromDate(String fromDate) {
    this.fromDate = fromDate;
  }

  public String getToDate() {
    return toDate;
  }

  public void setToDate(String toDate) {
    this.toDate = toDate;
  }

  public int getClientId() {
    return clientId;
  }

  public void setClientId(int clientId) {
    this.clientId = clientId;
  }

  @Override
  public String toString() {
    return "RecruitVo{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", fromDate='" + fromDate + '\'' +
        ", toDate='" + toDate + '\'' +
        ", clientId=" + clientId +
        '}';
  }
}
