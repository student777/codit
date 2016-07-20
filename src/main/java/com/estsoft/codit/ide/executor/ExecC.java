package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;

class ExecC extends Exec {

  ExecC(SourceCodeVo sourceCodeVo){
    super(sourceCodeVo, "\\task.c");
    this.compileCommand = new String[] {"cmd.exe", "/c", "gcc", "C:\\sourcecode\\" + sourceCodeVo.getId() + "\\task.c"};
    this.runtimeCommand = new String[] {"cmd.exe", "/c", "C:\\sourcecode\\" + sourceCodeVo.getId(), "task.exe"};
  }


  @Override
  public String run(TestCaseVo testCaseVo) {
   return "";
  }

  @Override
  public ExecResultInfo mark(TestCaseVo testCaseVo) {
    return null;
  }
}
