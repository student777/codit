package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;

class ExecJava extends Exec {

  ExecJava(SourceCodeVo sourceCodeVo) {
    super(sourceCodeVo, "/task.java");
    this.compileCommand = new String[] {"javac -encoding UTF-8 /home/codit/sourcecode/" +  sourceCodeVo.getId() + "/task.java"};
    this.runtimeCommand = new String[] {"java -cp", "/home/codit/sourcecode/" +  sourceCodeVo.getId(), " task"};
  }

  @Override
  public String run(TestCaseVo testCaseVo) {
    String compileOutput;
    compileOutput = execCommand2(compileCommand);
    String runtimeOutput = execCommandWithTestCase(testCaseVo);

    if(compileOutput.equals("")){
      //컴파일 성공시 컴파일의 결과는 "".  런타임 결과를 보여줌
      return runtimeOutput;
    }
    else if (runtimeOutput.equals("")) {
      //컴파일 오류시 런타임의 결과는 "".  컴파일 에러를 읽어서 가져옴
      return compileOutput;
    }
    else {
      return "이건 나와선 안되는 결과야";
    }
  }

  @Override
  public ExecResultInfo mark(TestCaseVo testCaseVo) {
    ExecResultInfo execResultInfo = new ExecResultInfo();
    Runtime runtime = Runtime.getRuntime();

    //compile
    String compileOutput = null;
    compileOutput = execCommand2(compileCommand);

    //TODO: 컴파일 실패 시 return null
    if(! compileOutput.equals("")){
      execResultInfo.setCompileOutput(compileOutput);
      return execResultInfo;
    }
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
