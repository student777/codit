package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.ResultVo;
import com.estsoft.codit.db.vo.SourceCodeVo;
import com.estsoft.codit.db.vo.TestCaseVo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


/*
 프로세스를 열고 언어 별로 cmd상에서 실행
 */
public abstract class Exec {
  SourceCodeVo sourceCodeVo;
  String compileOutput ;
  String[] compileCommand ;
  String runtimeOutput ;
  String[] runtimeCommand ;
  String filename;

  public abstract String run(TestCaseVo testCaseVo);
  public abstract ResultVo mark();


  public void write(){
    //경로와 파일명 지정
    int sourceCodeId = sourceCodeVo.getId();
    String filePath = "C:\\sourcecode\\" + sourceCodeId;

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




  /*
   * test case 없이 실행하는 경우
   * 이쪽은 한글 꺠지는 것 없이 잘 된다
   * TODO: scanner나 input()이 코드에 있는데 testCase를 안넣어주면 응답 없음
   */
  // test case 없이 실행
  String execCommand(String[] command) throws IOException, InterruptedException {
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
  String execCommand(String[] command, TestCaseVo testCaseVo) throws IOException, InterruptedException {
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
    //TODO: 테스트케이스에 대한 출력은 1줄짜리로 제한
    return sb.toString().replace("\n", "").replace("\r", "");
  }

  /*
  * FIXME:
  * [java] Scanner가 있는, 없는 코드에서 sysout으로 출력한 한글과 testCase에 포함된 한글이 깨진다
  * [python] input()이 없어서 teseCase가 필요없는데 프론트에서 testcase 값을 줘서 이쪽으로 오면 문자 깨짐
  */
  String execCommand2(String[] command, TestCaseVo testCaseVo) throws IOException, InterruptedException {
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
    return sb.toString().replace("\n", "").replace("\r", "");
  }

  String readCompileOutput(SourceCodeVo sourceCodeVo){
    //TODO: sourceCodeId 에 해당하는 경로를 찾아서 compile_result.txt를 읽는다
    return null;
  }

}
