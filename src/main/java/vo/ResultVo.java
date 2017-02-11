package vo;


public class ResultVo {
    private int sourceCodeId;
    private int testCaseId;
    private boolean correctness;
    private int usedMemory;
    private int runningTime;

    public int getSourceCodeId() {
        return sourceCodeId;
    }

    public void setSourceCodeId(int sourceCodeId) {
        this.sourceCodeId = sourceCodeId;
    }

    public int getTestCaseId() {
        return testCaseId;
    }

    public void setTestCaseId(int testCaseId) {
        this.testCaseId = testCaseId;
    }

    public boolean isCorrectness() {
        return correctness;
    }

    public void setCorrectness(boolean correctness) {
        this.correctness = correctness;
    }

    public int getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(int usedMemory) {
        this.usedMemory = usedMemory;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public String toString() {
        return "ResultVo{" +
                "sourceCodeId=" + sourceCodeId +
                ", testCaseId=" + testCaseId +
                ", correctness=" + correctness +
                ", usedMemory=" + usedMemory +
                ", runningTime=" + runningTime +
                '}';
    }
}
