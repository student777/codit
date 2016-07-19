package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.ResultVo;
import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;

class ExecPython extends Exec {

  ExecPython(SourceCodeVo sourceCodeVo){
    this.runtimeCommand = new String[]{"cmd.exe", "/c", "python", "C:\\sourcecode\\" + sourceCodeVo.getId()+"\\task.py"};
    this.sourceCodeVo = sourceCodeVo;
    this.filename = "\\task.py";
  }

  @Override
  public String run(TestCaseVo testCaseVo) {
    //TODO: .....여기좀 없애고싶다
    runTestCase2(testCaseVo);
    return runtimeOutput;
  }

  @Override
  public ResultVo mark(TestCaseVo testCaseVo) {
    ResultVo resultVo = new ResultVo();

    //run and set time
    long startTime = System.nanoTime();
    runTestCase(testCaseVo);
    long endTime = System.nanoTime();
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
