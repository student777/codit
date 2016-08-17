package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.SourceCodeVo;

class ExecJava extends Exec {

  ExecJava(SourceCodeVo sourceCodeVo) {
    super(sourceCodeVo, "/task.java");
    this.compileCommand = new String[] {"/home/ubuntu/jdk1.8/bin/javac", "-encoding","UTF-8", "/home/ubuntu/www/codit/sourcecode/" + sourceCodeVo.getId() + "/task.java"};
    this.runtimeCommand = new String[] {"/home/ubuntu/jdk1.8/bin/java", "-cp", "/home/ubuntu/www/codit/sourcecode/"+sourceCodeVo.getId(), "task"};
  }
}
