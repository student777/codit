package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.SourceCodeVo;

public class ExecFactory {

  public Exec pick(int languageId, SourceCodeVo sourceCodeVo){
    Exec exec = null;
    if (languageId == 1) {
      exec = new ExecC(sourceCodeVo);
    } else if (languageId == 2) {
      exec = new ExecJava(sourceCodeVo);
    } else if (languageId == 3) {
      exec = new ExecPython(sourceCodeVo);
    } else {
      // DO something
    }
    return exec;
  }
}
