package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;


class ExecPython extends Exec {

  ExecPython(SourceCodeVo sourceCodeVo) {
    super(sourceCodeVo, "/task.py");
    this.runtimeCommand = new String[]{"/usr/local/bin/python2.7", "/home/webmaster/codit/sourcecode/" + sourceCodeVo.getId() + "/task.py", "" + sourceCodeVo.getId()};
  }

  //python만 유일하게 컴파일 과정이 없으므로 override해줌
  @Override
  public String run(TestCaseVo testCaseVo) {
    //python의 경우 다른 함수 이용
    return execCommand(runtimeCommand, testCaseVo, false, false).getOutput();
  }

  @Override
  public ExecResultInfo mark(TestCaseVo testCaseVo) {
    //컴파일 성공 시 채점: set output and running time and memory_used
    ExecResultInfo execResultInfo = execCommand(runtimeCommand, testCaseVo, true, false);
    return execResultInfo;
  }
}
