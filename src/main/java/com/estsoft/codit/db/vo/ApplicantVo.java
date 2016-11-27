package com.estsoft.codit.db.vo;

public class ApplicantVo {
  private int id;
  private String name;
  private String secretKey;

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

  public String getSecretKey() {
    return secretKey;
  }

  public void setSecretKey(String secretKey) {
    this.secretKey = secretKey;
  }

  @Override
  public String toString() {
    return "ApplicantVo{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", secretKey='" + secretKey + '\'' +
            '}';
  }
}
