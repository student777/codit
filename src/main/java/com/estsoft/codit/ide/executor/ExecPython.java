package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;

class ExecPython extends Exec {

  ExecPython(SourceCodeVo sourceCodeVo){
    super(sourceCodeVo, "/task.py");
    this.runtimeCommand = new String[]{"python", "/home/webmaster/codit/sourcecode/" + sourceCodeVo.getId()+"/task.py"};
  }

  //python만 유일하게 컴파일 과정이 없으므로 override해줌
  @Override
  public String run(TestCaseVo testCaseVo) {
    //TODO: .....여기좀 없애고싶다
    return execCommandWithTestCase2(testCaseVo).getOutput();
  }

  @Override
  public ExecResultInfo mark(TestCaseVo testCaseVo) {
    ExecResultInfo execResultInfo = new ExecResultInfo();
    Runtime runtime = Runtime.getRuntime();

    //measure time, memory usage
    long usedMemoryBefore = runtime.totalMemory() - runtime.freeMemory();
    long startTime = System.nanoTime();
    String runtimeOutput = execCommandWithTestCase(testCaseVo).getOutput();
    long endTime = System.nanoTime();
    long usedMemoryAfter = runtime.totalMemory() - runtime.freeMemory();

    //get KB, milliseconds
    int usedMemory = (int) (usedMemoryAfter-usedMemoryBefore) / 1024;
    int time = (int) (endTime - startTime) / 1000000 ;

    //set values
    execResultInfo.setRunningTime(time);
    execResultInfo.setUsedMemory(usedMemory);

    //set runtimeOutput
    execResultInfo.setOutput(runtimeOutput);
    return execResultInfo;
  }
}
