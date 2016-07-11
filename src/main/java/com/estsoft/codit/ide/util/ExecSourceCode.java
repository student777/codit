package com.estsoft.codit.ide.util;

import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ExecSourceCode {
  public String execJava(SourceCodeVo sourceCodeVo, TestCaseVo testCaseVo) {
    int sourceCodeId = sourceCodeVo.getId();
    String[] compileCommand = {"cmd.exe", "/c", "javac  -encoding UTF-8", "C:\\sourcecode\\" + sourceCodeId + "\\task.java",  ">>", "C:\\sourcecode\\" + sourceCodeId + "\\compile_result.txt", "2<&1"};
    String[] runtimeCommand = {"cmd.exe", "/c", "java -cp", "C:\\sourcecode\\" + sourceCodeId, "task"};
    String compileOutput = null;
    String runtimeOutput = null;
    try {
      compileOutput = execCommand(compileCommand);
      runtimeOutput = execCommand(runtimeCommand, testCaseVo);
    } catch (Exception e) {
      e.printStackTrace();
    }

    //TODO: javac의 실행결과를 process의 inpustream으로 못 가져와서 파일 입출력 방식으로 함
    if(readCompileOutput(sourceCodeId)!=null){
      //컴파일 오류시 컴파일 결과를 읽어서 가져옴
      return "compile error(NOT PRINTED)\n"+compileOutput;
    }
    else if (!runtimeOutput.equals("")) {
      //컴파일성공 후 런타임 결과가 있을 때 결과를 보여줌
      return runtimeOutput;
    }
    else {
      return "이건 나와선 안되는 결과야";
    }
  }

  public String execC(SourceCodeVo sourceCodeVo, TestCaseVo testCaseVo) {
    int sourceCodeId = sourceCodeVo.getId();
    String[] compileCommand = {"cmd.exe", "/c", "gcc", "C:\\sourcecode\\" + sourceCodeId + "\\task.c"};
    String[] runtimeCommand = {"cmd.exe", "/c", "C:\\sourcecode\\" + sourceCodeId, "task.exe"};
    String compileOutput = null;
    String runtimeOutput = null;
    try {
      compileOutput = execCommand(compileCommand);
      runtimeOutput = execCommand(runtimeCommand, testCaseVo);
    } catch (Exception e) {
      e.printStackTrace();
    }

    if (!runtimeOutput.equals("")) {
      return runtimeOutput;
    }
    else return "compile error(NOT PRINTED)"+compileOutput;
  }

  public String execPython(SourceCodeVo sourceCodeVo, TestCaseVo testCaseVo) {
    int sourceCodeId = sourceCodeVo.getId();
    String[] command = {"cmd.exe", "/c", "python", "C:\\sourcecode\\" + sourceCodeId +"\\task.py"};
    String output = null;
    try {
      output = execCommand(command, testCaseVo);
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

  public String execCommand(String[] command, TestCaseVo testCaseVo) throws IOException, InterruptedException {
    Runtime runtime = Runtime.getRuntime();
    Process process = runtime.exec(command);
    OutputStream out = process.getOutputStream();
    out.write(testCaseVo.getInput().getBytes("UTF-8"));
    out.write("\n".getBytes());
    out.flush();
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

  public String readCompileOutput(int sourceCodeId){
    //sourceCodeId 에 해당하는 경로를 찾아서 compile_result.txt를 읽는다
    return null;
  }


}
