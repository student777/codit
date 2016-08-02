package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.SourceCodeVo;

class ExecJava extends Exec {

  ExecJava(SourceCodeVo sourceCodeVo) {
    super(sourceCodeVo, "/task.java");
    this.compileCommand = new String[] {"javac -encoding UTF-8 /home/webmaster/codit/sourcecode/" +  sourceCodeVo.getId() + "/task.java"};
    this.runtimeCommand = new String[] {"java -cp", "/home/codit/sourcecode/" +  sourceCodeVo.getId(), " task"};
  }
}
