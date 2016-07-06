package com.estsoft.codit.ide.util;

import com.estsoft.codit.db.vo.SourceCodeVo;

import java.io.IOException;
import java.io.InputStream;

public class ExecSourceCode {
  public String execJava(SourceCodeVo sourceCodeVo) {
    int sourceCodeId = sourceCodeVo.getId();
    String[] compileCommand = {"cmd.exe", "/c", "javac", "C:\\sourcecode\\" + sourceCodeId + "\\task.java"};
    String[] runtimeCommand = {"cmd.exe", "/c", "java -cp", "C:\\sourcecode\\" + sourceCodeId, "task"};
    String compileOutput = null;
    String runtimeOutput = null;
    try {
      compileOutput = execCommand(compileCommand);
      runtimeOutput = execCommand(runtimeCommand);
    } catch (Exception e) {
      e.printStackTrace();
    }

    //TODO: javac의 실행결과를 compileOutput에 받아올 수 없다
    if (!runtimeOutput.equals("")) {
      return runtimeOutput;
    }
    else return "compile error(NOT PRINTED)"+compileOutput;
  }

  public String execC(SourceCodeVo sourceCodeVo) {
    int sourceCodeId = sourceCodeVo.getId();
    String[] compileCommand = {"cmd.exe", "/c", "gcc", "C:\\sourcecode\\" + sourceCodeId + "\\task.c"};
    String[] runtimeCommand = {"cmd.exe", "/c", "C:\\sourcecode\\" + sourceCodeId, "task.exe"};
    String compileOutput = null;
    String runtimeOutput = null;
    try {
      compileOutput = execCommand(compileCommand);
      runtimeOutput = execCommand(runtimeCommand);
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (!runtimeOutput.equals("")) {
      return runtimeOutput;
    }
    else return "compile error(NOT PRINTED)"+compileOutput;
  }

  public String execPython(SourceCodeVo sourceCodeVo) {
    int sourceCodeId = sourceCodeVo.getId();
    String[] command = {"cmd.exe", "/c", "python", "C:\\sourcecode\\" + sourceCodeId +"\\task.py"};
    String output = null;
    try {
      output = execCommand(command);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return output;
  }


  public String execCommand(String[] command) throws IOException, InterruptedException {
    Runtime runtime = Runtime.getRuntime();
    Process process = runtime.exec(command);
    process.waitFor();
    int i;
    StringBuffer sb = new StringBuffer();
    byte[] b = new byte[4096];
    InputStream inputStream = process.getInputStream();
    while( (i = inputStream.read(b)) != -1){
      sb.append(new String(b, 0, i));
    }
    return sb.toString();
  }

}
