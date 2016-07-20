package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;

class ExecPython extends Exec {

  ExecPython(SourceCodeVo sourceCodeVo){
    super(sourceCodeVo, "\\task.py");
    this.runtimeCommand = new String[]{"cmd.exe", "/c", "python", "C:\\sourcecode\\" + sourceCodeVo.getId()+"\\task.py"};
  }

  @Override
  public String run(TestCaseVo testCaseVo) {
    //TODO: .....여기좀 없애고싶다
    return execCommandWithTestCase2(testCaseVo);
  }

  @Override
  public ExecResultInfo mark(TestCaseVo testCaseVo) {
    ExecResultInfo execResultInfo = new ExecResultInfo();
    Runtime runtime = Runtime.getRuntime();

    //measure time, memory usage
    long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
    long startTime = System.nanoTime();
    String runtimeOutput = execCommandWithTestCase(testCaseVo);
    long endTime = System.nanoTime();
    long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();

    //get KB, milliseconds
    int usedMemory = (int) (usedMemoryAfter-usedMemoryBefore) / 1024;
    int time = (int) (endTime - startTime) / 1000000 ;

    //set values
    execResultInfo.setRunningTime(time);
    execResultInfo.setUsedMemory(usedMemory);

    //set runtimeOutput
    execResultInfo.setRuntimeOutput(runtimeOutput);
    return execResultInfo;
  }
}
