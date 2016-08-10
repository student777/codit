package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.SourceCodeVo;

class ExecC extends Exec {

  ExecC(SourceCodeVo sourceCodeVo){
    super(sourceCodeVo, "/task.c");
    this.compileCommand = new String[] {"/usr/bin/gcc", "-o", "/home/webmaster/codit/sourcecode/"+sourceCodeVo.getId()+"/task", "/home/webmaster/codit/sourcecode/" + sourceCodeVo.getId() + "/task.c"};
    this.runtimeCommand = new String[] {"/home/webmaster/codit/sourcecode/" + sourceCodeVo.getId()+"/task"};
  }
}
