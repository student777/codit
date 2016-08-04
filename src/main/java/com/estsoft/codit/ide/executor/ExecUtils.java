package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.SourceCodeVo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

public class ExecUtils {

  public static String getStringFromProcess(Process process) {
    //get exec output
    InputStream errorStream = process.getErrorStream();
    InputStream inputStream = process.getInputStream();
    String errorOutput = getStringFromStream(errorStream);
    String output = getStringFromStream(inputStream);
    if(!errorOutput.equals("")){
      return errorOutput;
    }
    else {
      return output;
    }
  }

  public static String getStringFromProcess2(Process process) {
    //get exec output
    InputStream errorStream = process.getErrorStream();
    InputStream inputStream = process.getInputStream();
    String errorOutput = getStringFromStream2(errorStream);
    String output = getStringFromStream2(inputStream);
    if(!errorOutput.equals("")){
      return errorOutput;
    }
    else {
      return output;
    }
  }



  /*
  * FIXME
  * [java] Scanner가 코드에 있어 testCase가 필요한 문제의 경우 testcase의 한글이 깨져서 나온다
  * [python] print(input()) 과 같은 코드에서 한글로 된 tesetCase를 넣어주면 한글이 깨진다. 영어는 잘됨
  */
  private static String getStringFromStream(InputStream is){
    int i;
    byte[] b = new byte[4096];
    StringBuilder sb = new StringBuilder();
    try {
      while( (i = is.read(b)) != -1){
        sb.append(new String(b, 0, i));
      }
      is.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return sb.toString().replace("\n", "").replace("\r", "");
  }

  /*
  * FIXME:
  * [java] Scanner가 있는, 없는 코드에서 sysout으로 출력한 한글과 testCase에 포함된 한글이 깨진다
  * [python] input()이 없어서 teseCase가 필요없는데 프론트에서 testcase 값을 줘서 이쪽으로 오면 문자 깨짐
  */
  //TODO: 테스트케이스에 대한 출력은 1줄짜리로 제한
  private static String getStringFromStream2(InputStream is){
    int i;
    StringBuilder sb = new StringBuilder();
    InputStreamReader isr;
    try {
      isr = new InputStreamReader(is, "UTF-8");
      while( (i = isr.read()) != -1){
        sb.append( (char)i );
      }
      is.close();
    } catch (UnsupportedEncodingException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return sb.toString().replace("\n", "").replace("\r", "");
  }


  public static int getMemoryUsedFromFile(SourceCodeVo sourceCodeVo){
    byte[] b = new byte[4];
    FileInputStream input;
    try {
      input = new FileInputStream("/home/webmaster/codit/sourcecode/"+sourceCodeVo.getId()+"/memory.txt");
      input.read(b);
      input.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Integer.parseInt(new String(b));
  }
}
