package vo;

public class SourceCodeVo {
    private int id;
    private String code;
    private int applicantId;
    private int problemId;

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

    @Override
    public String toString() {
        return "SourceCodeVo{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", applicantId=" + applicantId +
                ", problemId=" + problemId +
                '}';
    }
}
