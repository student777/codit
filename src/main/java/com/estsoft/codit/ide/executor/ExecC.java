package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.ProblemVo;
import com.estsoft.codit.db.vo.SourceCodeVo;

import static com.estsoft.codit.ide.executor.ExecUtils.WORKSPACE_PATH;

class ExecC extends Exec {

  ExecC(SourceCodeVo sourceCodeVo, ProblemVo problemVo){
    super(sourceCodeVo, problemVo, "/task.c", "/main.c");
    this.compileCommand = new String[] {"/usr/bin/gcc", "-o", WORKSPACE_PATH+"sourcecode/"+sourceCodeVo.getId()+"/task", WORKSPACE_PATH+"sourcecode/" + sourceCodeVo.getId() + "/main.c"};
    this.runtimeCommand = new String[] {WORKSPACE_PATH+"sourcecode/" + sourceCodeVo.getId()+"/task"};
  }
}
