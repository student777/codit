package com.estsoft.codit.ide.util;

import com.estsoft.codit.db.vo.SourceCodeVo;

import java.io.IOException;

public class ExecTest {
    public void exec(SourceCodeVo sourceCodeVo) {
      int sourceCodeId = sourceCodeVo.getId();
      String[] compileCommand = {"cmd.exe", "/c", "javac", "C:\\sourcecode\\"+sourceCodeId+"\\task.java", ">>", "C:\\sourcecode\\"+sourceCodeId+"\\compile_output.txt", "2<&1" };
      String[] runtimeCommand = {"cmd.exe", "/c", "java -cp C:\\sourcecode\\"+sourceCodeId, "task",  ">>", "C:\\sourcecode\\"+sourceCodeId+"\\runtime_output.txt", "2<&1" };
    try {
      byRuntime(compileCommand);
      byRuntime(runtimeCommand);
    }
    catch(Exception e){
      e.printStackTrace();
    }
  }

  public void byRuntime(String[] command) throws IOException, InterruptedException {
    Runtime runtime = Runtime.getRuntime();
    Process process = runtime.exec(command);
    process.waitFor();
    //printStream(process);
  }

/*
 * 컴파일, 실행결과를 파일로 저장하므로 System.out으로 출력할 필요가 없다
 * TODO: 파일로 저장하지 않고 string으로 리턴해서 controller에 토스할 것

  private void printStream(Process process) throws IOException, InterruptedException {
    process.waitFor();
    try{
      InputStream psout = process.getInputStream();
      copy(psout, System.out);
    } catch(Exception e){
      e.printStackTrace();
    }
  }

  public void copy(InputStream input, OutputStream output) throws IOException {
    byte[] buffer = new byte[1024];
    int n = 0;
    while ((n = input.read(buffer)) != -1) {
      output.write(buffer, 0, n);
    }
  }


*/
}
