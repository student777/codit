package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.ResultVo;
import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;

class ExecC extends Exec {

  ExecC(SourceCodeVo sourceCodeVo){
    this.compileCommand = new String[] {"cmd.exe", "/c", "gcc", "C:\\sourcecode\\" + sourceCodeVo.getId() + "\\task.c"};
    this.runtimeCommand = new String[] {"cmd.exe", "/c", "C:\\sourcecode\\" + sourceCodeVo.getId(), "task.exe"};
    this.sourceCodeVo = sourceCodeVo;
    this.filename = "\\task.c";
  }


  @Override
  public String run(TestCaseVo testCaseVo) {
    try {
      compileOutput = execCommand(compileCommand);
      runtimeOutput = execCommand(runtimeCommand, testCaseVo);
    } catch (Exception e) {
      e.printStackTrace();
    }

    if(runtimeOutput==null){
      return "여기는들어와선 안돼";
    }
    if (!runtimeOutput.equals("")) {
      return runtimeOutput;
    }
    else return "compile error(NOT PRINTED)"+compileOutput;
  }


  @Override
  public ResultVo mark() {
    return null;
  }
}
