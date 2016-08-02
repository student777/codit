package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;

class ExecJava extends Exec {

  ExecJava(SourceCodeVo sourceCodeVo) {
    super(sourceCodeVo, "/task.java");
    this.compileCommand = new String[] {"javac -encoding UTF-8 /home/webmaster/codit/sourcecode/" +  sourceCodeVo.getId() + "/task.java"};
    this.runtimeCommand = new String[] {"java -cp", "/home/codit/sourcecode/" +  sourceCodeVo.getId(), " task"};
  }

  @Override
  public ExecResultInfo mark(TestCaseVo testCaseVo) {
    ExecResultInfo execResultInfo = new ExecResultInfo();
    Runtime runtime = Runtime.getRuntime();

    //compile
    String compileOutput = null;
    compileOutput = execCommand2(compileCommand).getOutput();

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
