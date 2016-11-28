package com.estsoft.codit.db.vo;

public class SourceCodeVo {
    private int id;
    private String code;
    private int applicantId;
    private int problemId;
    private String created_at;

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

    public int getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(int applicantId) {
        this.applicantId = applicantId;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    @Override
    public String toString() {
        return "SourceCodeVo{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", applicantId=" + applicantId +
                ", problemId=" + problemId +
                ", created_at='" + created_at + '\'' +
                '}';
    }
}
