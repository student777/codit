package com.estsoft.codit.ide.executor;

import static com.estsoft.codit.ide.executor.ExecUtils.getMemoryUsedFromFile;

import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

class ExecPython extends Exec {

  ExecPython(SourceCodeVo sourceCodeVo) {
    super(sourceCodeVo, "/task.py");
    this.runtimeCommand = new String[]{"/usr/local/bin/python2.7", "/home/webmaster/codit/sourcecode/" + sourceCodeVo.getId() + "/task.py", "" + sourceCodeVo.getId()};
  }

  //python 소스코드 하단에 메모리 측정 스크립트를 추가한다
  //run 할때나 mark할때나 구분없이 채점 스크립트가 추가됨
  @Override
  public void write(SourceCodeVo sourceCodeVo, String filename) {
    //경로와 파일명 지정
    int sourceCodeId = sourceCodeVo.getId();
    String filePath = "/home/webmaster/codit/sourcecode/" + sourceCodeId;

    // sourcecode/sourcdcodeId/memory.txt에 VmPeak 저장
    String checkMemoryScript = "import os\n" +
        "import subprocess\n" +
        "command=\'cat /proc/\' + str(os.getpid()) + \'/status | grep VmPeak | grep -o \"[0-9][0-9]*\"\'\n" +
        "direct_output = subprocess.check_output(command, shell=True)\n" +
        "import sys\n" +
        "f = open(\'/home/webmaster/codit/sourcecode/\'+sys.argv[1]+\'/memory.txt\', \'w\')\n" +
        "f.write(direct_output)\n" +
        "f.close()";

    //경로지정,  파일 생성
    try {
      File file = new File(filePath);
      if (file.mkdirs()) {
        OutputStream os = new FileOutputStream(filePath + filename);
        String fullSourceCode = sourceCodeVo.getCode() + "\n" + checkMemoryScript;
        byte[] data = fullSourceCode.getBytes("UTF-8");
        os.write(data);
      } else {
        //여기는 들어오면 안돼
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

  }


  //python만 유일하게 컴파일 과정이 없으므로 override해줌
  @Override
  public String run(TestCaseVo testCaseVo) {
    //python의 경우 다른 함수 이용
    return execCommand(runtimeCommand, testCaseVo, false, false).getOutput();
  }

  @Override
  public ExecResultInfo mark(TestCaseVo testCaseVo) {
    //컴파일 성공 시 채점: set output and running time
    ExecResultInfo execResultInfo = execCommand(runtimeCommand, testCaseVo, true, true);

    //set used memory
    int usedMemory = getMemoryUsedFromFile(sourceCodeVo);
    execResultInfo.setUsedMemory(usedMemory);

    return execResultInfo;

  }


}
