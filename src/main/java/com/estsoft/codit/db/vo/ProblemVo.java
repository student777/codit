package com.estsoft.codit.db.vo;

public class ProblemVo {
  private int id;
  private int language_id;
  private int problem_info_id;
  private String skeleton_code;
  private String main_code;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getLanguage_id() {
    return language_id;
  }

  public void setLanguage_id(int language_id) {
    this.language_id = language_id;
  }

  public int getProblem_info_id() {
    return problem_info_id;
  }

  public void setProblem_info_id(int problem_info_id) {
    this.problem_info_id = problem_info_id;
  }

  public String getSkeleton_code() {
    return skeleton_code;
  }

  public void setSkeleton_code(String skeleton_code) {
    this.skeleton_code = skeleton_code;
  }

  public String getMain_code() {
    return main_code;
  }

  public void setMain_code(String main_code) {
    this.main_code = main_code;
  }

  @Override
  public String toString() {
    return "ProblemVo{" +
        "id=" + id +
        ", language_id=" + language_id +
        ", problem_info_id=" + problem_info_id +
        ", skeleton_code='" + skeleton_code + '\'' +
        ", main_code='" + main_code + '\'' +
        '}';
  }
}
