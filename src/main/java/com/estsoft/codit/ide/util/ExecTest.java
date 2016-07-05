package com.estsoft.codit.ide.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ExecTest {
  public static void main(String[] args) throws IOException, InterruptedException {
    String[] command = new String[] { "cmd.exe", "/c", "javac", "test.java"};
    String[] command2 = new String[] { "cmd.exe", "/c", "java", "test"};
    ExecTest runner = new ExecTest();
    runner.byRuntime(command);
    runner.byRuntime(command2);
    // runner.byProcessBuilder(command);
  }

  public void byRuntime(String[] command) throws IOException, InterruptedException {
    Runtime runtime = Runtime.getRuntime();
    Process process = runtime.exec(command);
    printStream(process);
  }


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

  /*
  public void byProcessBuilder(String[] command) throws IOException,InterruptedException {
    ProcessBuilder builder = new ProcessBuilder(command);
    Process process = builder.start();
    printStream(process);
  }
  */

}
