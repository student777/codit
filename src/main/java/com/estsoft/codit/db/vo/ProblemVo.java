package com.estsoft.codit.db.vo;

public class ProblemVo {
    private int id;
    private int languageId;
    private int problemInfoId;
    private String skeletonCode;
    private String mainCode;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLanguageId() {
        return languageId;
    }

    public void setLanguageId(int languageId) {
        this.languageId = languageId;
    }

    public int getProblemInfoId() {
        return problemInfoId;
    }

    public void setProblemInfoId(int problemInfoId) {
        this.problemInfoId = problemInfoId;
    }

    public String getSkeletonCode() {
        return skeletonCode;
    }

    public void setSkeletonCode(String skeletonCode) {
        this.skeletonCode = skeletonCode;
    }

    public String getMainCode() {
        return mainCode;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    @Override
    public String toString() {
        return "ProblemVo{" +
                "id=" + id +
                ", languageId=" + languageId +
                ", problemInfoId=" + problemInfoId +
                ", skeletonCode='" + skeletonCode + '\'' +
                ", mainCode='" + mainCode + '\'' +
                '}';
    }
}
