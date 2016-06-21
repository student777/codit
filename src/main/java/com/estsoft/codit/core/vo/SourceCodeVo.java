package com.estsoft.codit.core.vo;

public class SourceCodeVo {
  int id;
  String code;
  int applicant_id;
  int program_code_id;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public int getApplicant_id() {
    return applicant_id;
  }

  public void setApplicant_id(int applicant_id) {
    this.applicant_id = applicant_id;
  }

  public int getProgram_code_id() {
    return program_code_id;
  }

  public void setProgram_code_id(int program_code_id) {
    this.program_code_id = program_code_id;
  }

  @Override
  public String toString() {
    return "SourceCodeVo{" +
        "id=" + id +
        ", code='" + code + '\'' +
        ", applicant_id=" + applicant_id +
        ", program_code_id=" + program_code_id +
        '}';
  }
}
