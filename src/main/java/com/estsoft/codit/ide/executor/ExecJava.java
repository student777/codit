package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.SourceCodeVo;

class ExecJava extends Exec {

  ExecJava(SourceCodeVo sourceCodeVo) {
    super(sourceCodeVo, "/task.java");
    this.compileCommand = new String[] {"/usr/local/futures/jdk1.8/bin/javac", "-encoding","UTF-8", "/home/webmaster/codit/sourcecode/" + sourceCodeVo.getId() + "/task.java"};
    this.runtimeCommand = new String[] {"/usr/local/futures/jdk1.8/bin/java", "-cp", "/home/webmaster/codit/sourcecode/"+sourceCodeVo.getId(), "task"};
  }
}
