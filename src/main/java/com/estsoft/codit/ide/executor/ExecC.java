package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;

class ExecC extends Exec {

  ExecC(SourceCodeVo sourceCodeVo){
    super(sourceCodeVo, "/task.c");
    this.compileCommand = new String[] {"gcc -o task", "/home/webmaster/codit/sourcecode/" + sourceCodeVo.getId() + "/task.c"};
    this.runtimeCommand = new String[] {"/home/webmaster/codit/sourcecode/" + sourceCodeVo.getId(), "/task"};
  }

  @Override
  public ExecResultInfo mark(TestCaseVo testCaseVo) {
    ExecResultInfo execResultInfo = new ExecResultInfo();
    Runtime runtime = Runtime.getRuntime();

    //compile
    String compileOutput = execCommand(compileCommand).getOutput();

    //TODO: 컴파일 실패 시 return null
    if(! compileOutput.equals("")){
      execResultInfo.setOutput(compileOutput);
      return execResultInfo;
    }
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
