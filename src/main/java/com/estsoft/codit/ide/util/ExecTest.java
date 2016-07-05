package com.estsoft.codit.ide.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ExecTest {
    public void exec(String command) {
    //String[] x = {"cmd.exe", "/c", "javac", "test.java", "&", "java", " test"};
//      String[] x = {"cmd.exe", "/c", "echo", "%PATH%", "&", "javac","test.java", "&", "java", "-cp C:\\Users\\Malzahar\\lib\\apache-tomcat-8.0.35\\bin", "test" };
      String[] x = {"cmd.exe", "/c", "java -cp C:\\Users\\Malzahar\\lib\\apache-tomcat-8.0.35\\bin test"};
    try {
      byRuntime(x);
      //byProcessBuilder(x);
    }
    catch(Exception e){
      e.printStackTrace();
    }
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

//  public void exec(String command) {
//    //String[] x = {"cmd.exe", "/c", "javac", "test.java", "&", "java", " test"};
//    String[] x = {"cmd.exe", "/c", "echo", "%PATH%", "&", "javac","test.java", "&", "java", "test"};
//    ExecTest runner = new ExecTest();
//    try {
//      runner.byRuntime(x);
//    }
//    catch(Exception e){
//      e.printStackTrace();
//    }
//  }

//  public void exec(String command) {
//    //String[] x = {"cmd.exe", "/c", "javac", "test.java", "&", "java", " test"};
//    String[] x = {"cmd.exe", "/c", "echo", "%PATH%", "&", "javac","test.java", "&", "java", "test"};
//    try {
//      byRuntime(x);
//      //byProcessBuilder(x);
//    }
//    catch(Exception e){
//      e.printStackTrace();
//    }
//  }


//  public void exec(String command) {
//    List<String> prefix = new ArrayList<String>();
//    prefix.add("cmd.exe");
//    prefix.add("/c");
//    prefix.add(command);
//    try {
//      byRuntime(prefix.toArray(new String[0]));
//    }
//    catch(Exception e){
//      e.printStackTrace();
//    }
//    // runner.byProcessBuilder(command);
//  }

//  public static void main(String[] args) throws IOException, InterruptedException {
//    List<String> prefix = new ArrayList<String>();
//    prefix.add("cmd.exe");
//    prefix.add("/c");
//    prefix.add("javac test.java & java test");
//    ExecTest runner = new ExecTest();
//    runner.byRuntime(prefix.toArray(new String[0]));
//    // runner.byProcessBuilder(command);
//  }






  public void byProcessBuilder(String[] command) throws IOException,InterruptedException {
    ProcessBuilder builder = new ProcessBuilder(command);
    Process process = builder.start();
    printStream(process);
  }


}
