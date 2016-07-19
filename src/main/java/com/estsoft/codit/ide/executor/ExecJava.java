package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.ResultVo;
import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;

class ExecJava extends Exec {

  ExecJava(SourceCodeVo sourceCodeVo){
    this.compileCommand = new String[] {"cmd.exe", "/c", "javac  -encoding UTF-8", "C:\\sourcecode\\" +  sourceCodeVo.getId() + "\\task.java",  ">>", "C:\\sourcecode\\" +sourceCodeVo.getId() + "\\compile_result.txt", "2<&1"};
    this.runtimeCommand = new String[] {"cmd.exe", "/c", "java -cp", "C:\\sourcecode\\" +  sourceCodeVo.getId(), "task"};
    this.sourceCodeVo = sourceCodeVo;
    this.filename = "\\task.java";
  }

  @Override
  public String run(TestCaseVo testCaseVo) {
    compileOutput = execCommand(compileCommand);
    runTestCase(testCaseVo);

    //javac의 실행결과를 process의 inpustream으로 못 가져와서 파일 입출력 방식으로 함
    if(readCompileOutput(sourceCodeVo)!=null){
      //컴파일 오류시 컴파일 결과를 읽어서 가져옴
      return "compile error(NOT PRINTED)\n"+compileOutput;
    }
    else if (!runtimeOutput.equals("")) {
      //컴파일성공 후 런타임 결과가 있을 때 결과를 보여줌
      return runtimeOutput;
    }
    else {
      return "이건 나와선 안되는 결과야";
    }

  }

  @Override
  public ResultVo mark(TestCaseVo testCaseVo) {
    ResultVo resultVo = new ResultVo();

    //compile
    compileOutput = execCommand(compileCommand);

    //TODO: 컴파일 실패 시 return null
//    //javac의 실행결과를 process의 inpustream으로 못 가져와서 파일 입출력 방식으로 함
//    if(readCompileOutput(sourceCodeVo)!=null){
//      //컴파일 오류시 컴파일 결과를 읽어서 가져옴
//      return "compile error(NOT PRINTED)\n"+compileOutput;
//    }
//    else if (!runtimeOutput.equals("")) {
//      //컴파일성공 후 런타임 결과가 있을 때 결과를 보여줌
//      return runtimeOutput;
//    }
//    else {
//      return "이건 나와선 안되는 결과야";
//    }


    //run and set time
    long startTime = System.nanoTime();
    runTestCase(testCaseVo);
    long endTime = System.nanoTime();
    //get milliseconds
    int time = (int) (endTime - startTime) / 1000000 ;
    resultVo.setRunningTime(time);

    //set correctness
    if(testCaseVo.getAnswer().equals(runtimeOutput)){
      resultVo.setCorrectness(true);
    }
    else{
      resultVo.setCorrectness(false);
    }


    //TODO: set used_memory
    resultVo.setUsedMemory(777);
    return resultVo;
  }
}
