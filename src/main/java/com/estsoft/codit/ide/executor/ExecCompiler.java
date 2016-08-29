package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.SourceCodeVo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static com.estsoft.codit.ide.executor.ExecUtils.WORKSPACE_PATH;

public class ExecCompiler {
  private String[] runtimeCommand;
  private int sourceCodeId;

  public ExecCompiler(SourceCodeVo sourceCodeVo) {
    sourceCodeId = sourceCodeVo.getId();
    runtimeCommand = new String[]{"java", "-jar", WORKSPACE_PATH+"j-compiler/target/j-compiler-1.0-SNAPSHOT.jar", WORKSPACE_PATH +"sourcecode/" + sourceCodeId + "/Task.java"};
  }
  public String run() {
    StringBuilder stringBuilder = new StringBuilder();
    Runtime runtime = Runtime.getRuntime();
    try {
      Process process = runtime.exec(runtimeCommand);
      process.waitFor();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    BufferedReader br;
    try {
      br = new BufferedReader(new FileReader(WORKSPACE_PATH+"sourcecode/"+sourceCodeId+"/Task.out.json"));
      while(true) {
        String line = br.readLine();
        if (line==null) break;
        stringBuilder.append(line);
      }
      br.close();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    }

    return stringBuilder.toString();
  }

}
