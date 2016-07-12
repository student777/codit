package com.estsoft.codit.ide.util;

import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class ExecSourceCode {


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

  public String execJava(SourceCodeVo sourceCodeVo, TestCaseVo testCaseVo) throws IOException, InterruptedException {
    int sourceCodeId = sourceCodeVo.getId();
    String[] compileCommand = {"cmd.exe", "/c", "javac  -encoding UTF-8", "C:\\sourcecode\\" + sourceCodeId + "\\task.java",  ">>", "C:\\sourcecode\\" + sourceCodeId + "\\compile_result.txt", "2<&1"};
    String[] runtimeCommand = {"cmd.exe", "/c", "java -cp", "C:\\sourcecode\\" + sourceCodeId, "task"};
    String compileOutput;
    String runtimeOutput;
    compileOutput = execCommand(compileCommand);
    if(testCaseVo==null){
      runtimeOutput = execCommand(runtimeCommand);
    }
    else {
      runtimeOutput = execCommand(runtimeCommand, testCaseVo);
      //runtimeOutput = execCommand2(runtimeCommand, testCaseVo); 위에거가 그나마 나음
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


  public String execPython(SourceCodeVo sourceCodeVo, TestCaseVo testCaseVo) throws IOException, InterruptedException {
    int sourceCodeId = sourceCodeVo.getId();
    String[] command = {"cmd.exe", "/c", "python", "C:\\sourcecode\\" + sourceCodeId +"\\task.py"};
    String output;
    if(testCaseVo==null){
      output = execCommand(command);
    }
    else {
      output = execCommand2(command, testCaseVo);
      //output = execCommand(command, testCaseVo); 위에거가 그나마 나음
    }
    return output;
  }


  /*
   * test case 없이 실행하는 경우
   * 이쪽은 한글 꺠지는 것 없이 잘 된다
   * TODO: scanner나 input()이 코드에 있는데 testCase를 안넣어주면 응답 없음
   */
  // test case 없이 실행
  private String execCommand(String[] command) throws IOException, InterruptedException {
    Runtime runtime = Runtime.getRuntime();
    Process process = runtime.exec(command);
    process.waitFor();
    int i;
    StringBuilder sb = new StringBuilder();
    byte[] b = new byte[4096];
    InputStream inputStream = process.getInputStream();
    while( (i = inputStream.read(b)) != -1){
      sb.append(new String(b, 0, i));
    }
    return sb.toString();
  }

  /*
  * FIXME
  * [java] Scanner가 코드에 있어 testCase가 필요한 문제의 경우 testcase의 한글이 깨져서 나온다
  * [python] print(input()) 과 같은 코드에서 한글로 된 tesetCase를 넣어주면 한글이 깨진다. 영어는 잘됨
  */
  private String execCommand(String[] command, TestCaseVo testCaseVo) throws IOException, InterruptedException {
    Runtime runtime = Runtime.getRuntime();
    Process process = runtime.exec(command);
    OutputStream out = process.getOutputStream();

    /* writer까지 안 써줘도 된다
    Writer w = new OutputStreamWriter(out, "UTF-8");
    w.write(testCaseVo.getInput());
    w.write("\n");
    w.close();
     */
    out.write(testCaseVo.getInput().getBytes("UTF-8"));
    out.write("\n".getBytes());
    out.flush();
    process.waitFor();
    int i;
    StringBuilder sb = new StringBuilder();
    InputStream inputStream = process.getInputStream();
    byte[] b = new byte[4096];
    while( (i = inputStream.read(b)) != -1){
      sb.append(new String(b, 0, i));
    }
    return sb.toString();
  }

  /*
  * FIXME:
  * [java] Scanner가 있는, 없는 코드에서 sysout으로 출력한 한글과 testCase에 포함된 한글이 깨진다
  * [python] input()이 없어서 teseCase가 필요없는데 프론트에서 testcase 값을 줘서 이쪽으로 오면 문자 깨짐
  */
  private String execCommand2(String[] command, TestCaseVo testCaseVo) throws IOException, InterruptedException {
    Runtime runtime = Runtime.getRuntime();
    Process process = runtime.exec(command);
    OutputStream out = process.getOutputStream();
    out.write(testCaseVo.getInput().getBytes("UTF-8"));
    out.write("\n".getBytes());
    out.flush();
    process.waitFor();
    int i;
    StringBuilder sb = new StringBuilder();
    InputStream inputStream = process.getInputStream();
    InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
    while( (i = isr.read()) != -1){
      sb.append( (char)i );
    }
    return sb.toString();
  }

  private String readCompileOutput(int sourceCodeId){
    //sourceCodeId 에 해당하는 경로를 찾아서 compile_result.txt를 읽는다
    return null;
  }


}
