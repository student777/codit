package com.estsoft.codit.ide.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteTest {

  public void write(String code){
    try{
      OutputStream os = new FileOutputStream("test.java");
      byte[] data = code.getBytes();
      os.write(data);
    }
    catch(FileNotFoundException e){
      e.printStackTrace();
    }
    catch(IOException e){
      e.printStackTrace();
    }

  }
}
