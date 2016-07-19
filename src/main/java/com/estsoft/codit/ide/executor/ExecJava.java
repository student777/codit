package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.ResultVo;
import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;

import java.io.IOException;

class ExecJava extends Exec {

  ExecJava(SourceCodeVo sourceCodeVo){
    this.compileCommand = new String[] {"cmd.exe", "/c", "javac  -encoding UTF-8", "C:\\sourcecode\\" +  sourceCodeVo.getId() + "\\task.java",  ">>", "C:\\sourcecode\\" +sourceCodeVo.getId() + "\\compile_result.txt", "2<&1"};
    this.runtimeCommand = new String[] {"cmd.exe", "/c", "java -cp", "C:\\sourcecode\\" +  sourceCodeVo.getId(), "task"};
    this.sourceCodeVo = sourceCodeVo;
    this.filename = "\\task.java";
  }

  @Override
  public String run(TestCaseVo testCaseVo) {
    try {
      compileOutput = execCommand(compileCommand);
      if(testCaseVo==null){
        runtimeOutput = execCommand(runtimeCommand);
      }
      else {
        runtimeOutput = execCommand(runtimeCommand, testCaseVo);
        //runtimeOutput = execCommand2(runtimeCommand, testCaseVo); 위에거가 그나마 나음
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
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
  public ResultVo mark() {
    return null;
  }
}
