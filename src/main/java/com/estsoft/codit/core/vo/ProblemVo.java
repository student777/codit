package com.estsoft.codit.core.vo;

public class ProblemVo {
  int id;
  int language_id;
  int problem_info;
  String skeleton_code;
  String main_code;

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

  public int getProblem_info() {
    return problem_info;
  }

  public void setProblem_info(int problem_info) {
    this.problem_info = problem_info;
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
        ", problem_info=" + problem_info +
        ", skeleton_code='" + skeleton_code + '\'' +
        ", main_code='" + main_code + '\'' +
        '}';
  }
}
