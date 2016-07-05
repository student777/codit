package com.estsoft.codit.ide.util;

import com.estsoft.codit.db.vo.SourceCodeVo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteTest {

  public void write(SourceCodeVo sourceCodeVo){
    int sourceCodeId = sourceCodeVo.getId();
    String filePath = "C:\\sourcecode\\" + sourceCodeId;
    try{
      File file = new File(filePath);
      file.mkdirs();
      OutputStream os = new FileOutputStream(filePath+"\\task.java");
      byte[] data = sourceCodeVo.getCode().getBytes();
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
