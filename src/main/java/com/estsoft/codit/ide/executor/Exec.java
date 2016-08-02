package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;


/*
 프로세스를 열고 언어 별로 cmd상에서 실행
 */
public abstract class Exec {
  public SourceCodeVo sourceCodeVo;
  public String[] compileCommand ;
  public String[] runtimeCommand ;
  public abstract String run(TestCaseVo testCaseVo);
  public abstract ExecResultInfo mark(TestCaseVo testCaseVo);

  //객체 생성시 자동으로 저장소에 소스코드파일 생성
  public Exec(SourceCodeVo sourceCodeVo, String filename){
    this.sourceCodeVo = sourceCodeVo;
    write(sourceCodeVo, filename);
  }

  public void write(SourceCodeVo sourceCodeVo, String filename){
    //경로와 파일명 지정
    int sourceCodeId = sourceCodeVo.getId();
    String filePath = "/home/webmaster/codit/sourcecode/" + sourceCodeId;

    //경로지정,  파일 생성
    try{
      File file = new File(filePath);
      if(file.mkdirs()){
        OutputStream os = new FileOutputStream(filePath+filename);
        byte[] data = sourceCodeVo.getCode().getBytes("UTF-8");
        os.write(data);
      }
      else{
        //여기는 들어오면 안돼
      }
    }
    catch(FileNotFoundException e){
      e.printStackTrace();
    }
    catch(IOException e){
      e.printStackTrace();
    }
  }

  //얘좀 없애고 싶다
  //testCase가 있을때 없을때를 분류해주기 위해 만듬..
  //set runtimeOutput of this class
  String execCommandWithTestCase(TestCaseVo testCaseVo){
    String runtimeOutput ;
    if(testCaseVo==null){
      runtimeOutput = execCommand(runtimeCommand);
    }
    else {
      runtimeOutput = execCommand(runtimeCommand, testCaseVo);
    }
    return runtimeOutput;
  }

  //여기로 좀 안 왔으면 좋겠다
  String execCommandWithTestCase2(TestCaseVo testCaseVo){
    String runtimeOutput;
    if(testCaseVo==null){
      runtimeOutput = execCommand(runtimeCommand);
    }
    else {
      runtimeOutput = execCommand2(runtimeCommand, testCaseVo);
    }
    return runtimeOutput;
  }


  /*
   * test case 없이 실행하는 경우
   * 이쪽은 한글 꺠지는 것 없이 잘 된다
   * TODO: scanner나 input()이 코드에 있는데 testCase를 안넣어주면 응답 없음
   */
  // test case 없이 실행
  String execCommand(String[] command) {
    Runtime runtime = Runtime.getRuntime();
    Process process = null;
    StringBuilder sb = new StringBuilder();
    try {
      process = runtime.exec(command);
      process.waitFor();
      int i;
      byte[] b = new byte[4096];
      InputStream inputStream = process.getInputStream();
      while( (i = inputStream.read(b)) != -1){
        sb.append(new String(b, 0, i));
      }
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    process.destroy();
    return sb.toString();
  }

  //컴파일 에러 잡기 위해 쓴다
  String execCommand2(String[] command) {
    Runtime runtime = Runtime.getRuntime();
    Process process = null;
    StringBuilder sb = new StringBuilder();
    try {
      process = runtime.exec(command);
      process.waitFor();
      int i;
      byte[] b = new byte[4096];
      InputStream inputStream = process.getErrorStream();
      while( (i = inputStream.read(b)) != -1){
        sb.append(new String(b, 0, i));
      }
      inputStream.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    process.destroy();
    return sb.toString();
  }


  /*
  * FIXME
  * [java] Scanner가 코드에 있어 testCase가 필요한 문제의 경우 testcase의 한글이 깨져서 나온다
  * [python] print(input()) 과 같은 코드에서 한글로 된 tesetCase를 넣어주면 한글이 깨진다. 영어는 잘됨
  */
  String execCommand(String[] command, TestCaseVo testCaseVo){
    Runtime runtime = Runtime.getRuntime();
    Process process = null;
    InputStream inputStream;
    StringBuilder sb = new StringBuilder();
    try {
      process = runtime.exec(command);
      OutputStream out = process.getOutputStream();
      out.write(testCaseVo.getInput().getBytes("UTF-8"));
      out.write("\n".getBytes());
      out.flush();
      process.waitFor();
      int i;
      inputStream = process.getInputStream();
      byte[] b = new byte[4096];
      while( (i = inputStream.read(b)) != -1){
        sb.append(new String(b, 0, i));
      }
      inputStream.close();
      out.close();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    process.destroy();
    //TODO: 테스트케이스에 대한 출력은 1줄짜리로 제한
    return sb.toString().replace("\n", "").replace("\r", "");
  }

  /*
  * FIXME:
  * [java] Scanner가 있는, 없는 코드에서 sysout으로 출력한 한글과 testCase에 포함된 한글이 깨진다
  * [python] input()이 없어서 teseCase가 필요없는데 프론트에서 testcase 값을 줘서 이쪽으로 오면 문자 깨짐
  */
  String execCommand2(String[] command, TestCaseVo testCaseVo) {
    Runtime runtime = Runtime.getRuntime();
    Process process = null;
    StringBuilder sb = null;
    OutputStream out;
    try {
      process = runtime.exec(command);
      out = process.getOutputStream();
      out.write(testCaseVo.getInput().getBytes("UTF-8"));
      out.write("\n".getBytes());
      out.flush();
      process.waitFor();
      sb = new StringBuilder();
      InputStream inputStream = process.getInputStream();
      InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
      int i;
      while( (i = isr.read()) != -1){
        sb.append( (char)i );
      }
      inputStream.close();
      out.close();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    process.destroy();
    return sb.toString().replace("\n", "").replace("\r", "");
  }

}
