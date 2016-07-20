package com.estsoft.codit.ide.executor;

public class ExecResultInfo {
  private String runtimeOutput;
  private String compileOutput;
  private int runningTime;
  private int usedMemory;

  public String getRuntimeOutput() {
    return runtimeOutput;
  }

  public void setRuntimeOutput(String runtimeOutput) {
    this.runtimeOutput = runtimeOutput;
  }

  public String getCompileOutput() {
    return compileOutput;
  }

  public void setCompileOutput(String compileOutput) {
    this.compileOutput = compileOutput;
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
        "runtimeOutput='" + runtimeOutput + '\'' +
        ", compileOutput='" + compileOutput + '\'' +
        ", runningTime=" + runningTime +
        ", usedMemory=" + usedMemory +
        '}';
  }
}
