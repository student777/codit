package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.SourceCodeVo;

import static com.estsoft.codit.ide.executor.ExecUtils.WORKSPACE_PATH;

class ExecJava extends Exec {

  ExecJava(SourceCodeVo sourceCodeVo) {
    super(sourceCodeVo, "/Task.java");
    this.compileCommand = new String[] {"/usr/bin/javac", "-encoding","UTF-8", WORKSPACE_PATH+"sourcecode/"+sourceCodeVo.getId()+"/Task.java"};
    this.runtimeCommand = new String[] {"/usr/bin/java", "-cp", WORKSPACE_PATH+"sourcecode/"+sourceCodeVo.getId(), "Task"};
  }
}
