package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.ProblemVo;
import com.estsoft.codit.db.vo.SourceCodeVo;

import static com.estsoft.codit.ide.executor.ExecUtils.WORKSPACE_PATH;

class ExecJava extends Exec {

  ExecJava(SourceCodeVo sourceCodeVo, ProblemVo problemVo) {
    super(sourceCodeVo, problemVo, "/Task.java", "/Main.java");
    this.compileCommand = new String[] {"/usr/bin/javac", "-cp", WORKSPACE_PATH+"sourcecode/" + sourceCodeVo.getId(), "-encoding","UTF-8", WORKSPACE_PATH+"sourcecode/"+sourceCodeVo.getId()+"/Main.java"};
    this.runtimeCommand = new String[] {"/usr/bin/java", WORKSPACE_PATH+"sourcecode/"+sourceCodeVo.getId(), "Main"};
  }
}
