package executor;

public class ExecResultInfo {
    private String output;
    private int runningTime;
    private int usedMemory;

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getRunningTime() {
        return runningTime;
    }

    public void setRunningTime(int runningTime) {
        this.runningTime = runningTime;
    }

    public int getUsedMemory() {
        return usedMemory;
    }

    public void setUsedMemory(int usedMemory) {
        this.usedMemory = usedMemory;
    }

    @Override
    public String toString() {
        return "ExecResultInfo{" +
                "output='" + output + '\'' +
                ", runningTime=" + runningTime +
                ", usedMemory=" + usedMemory +
                '}';
    }
}
