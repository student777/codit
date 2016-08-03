package com.estsoft.codit.ide.executor;

import static com.estsoft.codit.ide.executor.ExecUtils.getStringFromProcess;
import static com.estsoft.codit.ide.executor.ExecUtils.getStringFromProcess2;

import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;


/*
 프로세스를 열고 언어 별로 cmd상에서 실행
 */
public class Exec {
  public SourceCodeVo sourceCodeVo;
  public String[] compileCommand ;
  public String[] runtimeCommand ;

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

  public String run(TestCaseVo testCaseVo){
    String compileOutput;
    compileOutput = execCommand(compileCommand).getOutput();
    String runtimeOutput = execCommandWithTestCase(testCaseVo).getOutput();

    if(compileOutput.equals("")){
      //컴파일 성공시 컴파일의 결과는 "".  런타임 결과를 보여줌
      return runtimeOutput;
    }
    else if (runtimeOutput.equals("")) {
      //컴파일 오류시 런타임의 결과는 "".  컴파일 에러를 읽어서 가져옴
      return compileOutput;
    }
    else {
      return "이건 나와선 안되는 결과야";
    }
  }

  public ExecResultInfo mark(TestCaseVo testCaseVo){
    ExecResultInfo execResultInfo = new ExecResultInfo();

    //compile
    String compileOutput = execCommand(compileCommand).getOutput();

    //TODO: 컴파일 실패 시 return null
    if(! compileOutput.equals("")){
      execResultInfo.setOutput(compileOutput);
      return execResultInfo;
    }

    //컴파일 성공 시 채점
    execResultInfo = execCommandWithTestCase(testCaseVo);
    return execResultInfo;
  }

  //얘좀 없애고 싶다
  //testCase가 있을때 없을때를 분류해주기 위해 만듬..
  //set runtimeOutput of this class
  ExecResultInfo execCommandWithTestCase(TestCaseVo testCaseVo){
    ExecResultInfo execResultInfo;
    if(testCaseVo==null){
      execResultInfo = execCommand(runtimeCommand);
    }
    else {
      execResultInfo = execCommand(runtimeCommand, testCaseVo, true);
    }
    return execResultInfo;
  }

  //여기로 좀 안 왔으면 좋겠다
  ExecResultInfo execCommandWithTestCase2(TestCaseVo testCaseVo){
    ExecResultInfo execResultInfo;
    if(testCaseVo==null){
      execResultInfo = execCommand(runtimeCommand);
    }
    else {
      execResultInfo = execCommand(runtimeCommand, testCaseVo, false);
    }
    return execResultInfo;
  }


  /*
   * test case 없이 실행하는 경우
   * 이쪽은 한글 꺠지는 것 없이 잘 된다
   * TODO: scanner나 input()이 코드에 있는데 testCase를 안넣어주면 응답 없음
   */
  // test case 없이 실행
  ExecResultInfo execCommand(String[] command) {
    ExecResultInfo execResultInfo = new ExecResultInfo();
    Runtime runtime = Runtime.getRuntime();
    Process process = null;

    long startTime = System.nanoTime();
    try {
      process = runtime.exec(command);
      process.waitFor();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    long endTime = System.nanoTime();
    //get KB, milliseconds
    int time = (int) (endTime - startTime) / 1000000 ;
    //set values
    execResultInfo.setRunningTime(time);

    //set output
    String output = getStringFromProcess(process);
    execResultInfo.setOutput(output);
    process.destroy();

    //set used memory



    return execResultInfo;
  }




  ExecResultInfo execCommand(String[] command, TestCaseVo testCaseVo, boolean first){
    ExecResultInfo execResultInfo = new ExecResultInfo();
    Runtime runtime = Runtime.getRuntime();
    Process process = null;

    long startTime = System.nanoTime();
    try {
      process = runtime.exec(command);
      OutputStream out = process.getOutputStream();
      out.write(testCaseVo.getInput().getBytes("UTF-8"));
      out.write("\n".getBytes());
      out.flush();
      out.close();
      process.waitFor();
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    long endTime = System.nanoTime();
    //get KB, milliseconds
    int time = (int) (endTime - startTime) / 1000000 ;
    //set values
    execResultInfo.setRunningTime(time);

    //set output
    String output;
    if(first){
      output = getStringFromProcess(process);
    }
    else{
      output = getStringFromProcess2(process);
    }
    execResultInfo.setOutput(output);
    process.destroy();

    //set used memory


    return execResultInfo;
  }

}
