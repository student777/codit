package com.estsoft.codit.ide.util;

import com.estsoft.codit.db.vo.SourceCodeVo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class WriteTest {

  public void write(SourceCodeVo sourceCodeVo){
    try{
      OutputStream os = new FileOutputStream("sourcecode"+sourceCodeVo.getId() + ".java");
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
