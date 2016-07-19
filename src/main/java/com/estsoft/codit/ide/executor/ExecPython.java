package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.ResultVo;
import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;

import java.io.IOException;

class ExecPython extends Exec {

  ExecPython(SourceCodeVo sourceCodeVo){
    this.runtimeCommand = new String[]{"cmd.exe", "/c", "python", "C:\\sourcecode\\" + sourceCodeVo.getId()+"\\task.py"};
    this.sourceCodeVo = sourceCodeVo;
    this.filename = "\\task.py";
  }

  @Override
  public String run(TestCaseVo testCaseVo) {
    String output = null;
    try {
      if(testCaseVo==null){
        output = execCommand(runtimeCommand);
      }
      else {
        output = execCommand2(runtimeCommand, testCaseVo);
        //output = execCommand(command, testCaseVo); 위에거가 그나마 나음
      }
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    return output;


  }

  @Override
  public ResultVo mark() {
    return null;
  }
}
