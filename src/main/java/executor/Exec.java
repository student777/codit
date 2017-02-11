package executor;

import vo.ProblemVo;
import vo.SourceCodeVo;
import vo.TestCaseVo;

import java.io.*;
import java.lang.reflect.Field;

import static executor.ExecUtils.WORKSPACE_PATH;
import static executor.ExecUtils.getStringFromProcess;


/*
 프로세스를 열고 언어 별로 cmd상에서 실행
 */
public class Exec {

    public SourceCodeVo sourceCodeVo;
    public ProblemVo problemVo;
    public String[] compileCommand;
    public String[] runtimeCommand;


    public Exec(SourceCodeVo sourceCodeVo, ProblemVo problemVo, String srcFileName, String mainFileName) {
        this.sourceCodeVo = sourceCodeVo;
        this.problemVo = problemVo;
        write(sourceCodeVo, problemVo, srcFileName, mainFileName);
    }


    public void write(SourceCodeVo sourceCodeVo, ProblemVo problemVo, String srcFileName, String mainFileName) {
        //경로와 파일명 지정
        int sourceCodeId = sourceCodeVo.getId();
        String filePath = WORKSPACE_PATH + "sourcecode/" + sourceCodeId;

        //경로지정,  파일 생성
        try {
            File file = new File(filePath);
            if (file.mkdirs()) {
                //write user source code
                OutputStream os = new FileOutputStream(filePath + srcFileName);
                byte[] data = sourceCodeVo.getCode().getBytes("UTF-8");
                os.write(data);
                os.close();

                //write main code
                os = new FileOutputStream(filePath + mainFileName);
                data = problemVo.getMainCode().getBytes("UTF-8");
                os.write(data);
                os.close();

            } else {
                //여기는 들어오면 안돼
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String run(TestCaseVo testCaseVo) {
        String compileOutput;
        compileOutput = execCommand(compileCommand, null, false).getOutput();

        if (compileOutput.equals("") == false) {
            //컴파일 성공시 컴파일의 결과는 "".  런타임 결과를 보여줌
            return compileOutput;
        }
        String runtimeOutput = execCommand(runtimeCommand, testCaseVo, false).getOutput();
        return runtimeOutput;
    }

    public ExecResultInfo mark(TestCaseVo testCaseVo) {
        ExecResultInfo execResultInfo = new ExecResultInfo();

        //compile
        String compileOutput = execCommand(compileCommand, null, false).getOutput();

        if (!compileOutput.equals("")) {
            execResultInfo.setOutput(compileOutput);
            return execResultInfo;
        }

        //컴파일 성공 시 채점: set output and running time and used memory
        execResultInfo = execCommand(runtimeCommand, testCaseVo, true);
        return execResultInfo;
    }

    ExecResultInfo execCommand(String[] command, TestCaseVo testCaseVo, boolean isMark) {
        ExecResultInfo execResultInfo = new ExecResultInfo();
        Runtime runtime = Runtime.getRuntime();
        Process process = null;

        long startTime = 0L;
        long endTime = 0L;
        try {
            if (testCaseVo != null) {
                String[] splitTestCase = testCaseVo.getInput().split(" ");
                String[] commandWithArg = new String[command.length + splitTestCase.length + 1];

                //copy command to commandWithArg
                int i = 0;
                for (; i < command.length; i++)
                    commandWithArg[i] = command[i];

                // set test case answer into command
                commandWithArg[i++] = testCaseVo.getAnswer();

                // set test case input into command
                for (int j = 0; j < splitTestCase.length; i++, j++)
                    commandWithArg[i] = splitTestCase[j];

                command = commandWithArg;
            }

            //run process and check elapsed time
            startTime = System.nanoTime();
            process = runtime.exec(command);


//      //if execution time exceeds a second, kill process.
//      try {
//        process.waitFor(1L, TimeUnit.SECONDS);
//      } catch (InterruptedException e) {
//        e.printStackTrace();
//      }
//      if(process.isAlive()) {
//        System.out.println("alive!!");
//        process.destroy();
//        execResultInfo.setOutput("Time limit exeeds!");
//        execResultInfo.setRunningTime(9999);
//        return execResultInfo;
//      }

            endTime = System.nanoTime();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (isMark) {
            //get KB, milliseconds & set value
            int time = (int) (endTime - startTime) / 1000000;
            execResultInfo.setRunningTime(time);

            //get the PID on unix/linux systems
            int pid = 0;
            if (process.getClass().getName().equals("java.lang.UNIXProcess")) {
                try {
                    Field f = process.getClass().getDeclaredField("pid");
                    f.setAccessible(true);
                    pid = f.getInt(process);
                } catch (Throwable e) {
                    e.printStackTrace();
                }
            }
            //set memory used
            try {
                Process checkVmPeakProcess = runtime.exec(new String[]{"/bin/sh", "-c", "cat /proc/" + pid + "/status | grep VmPeak | grep -o [0-9][0-9]*"});
                checkVmPeakProcess.waitFor();
                int memory = Integer.parseInt(getStringFromProcess(checkVmPeakProcess).replace("\n", ""));
                execResultInfo.setUsedMemory(memory);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        //set output string
        String output = getStringFromProcess(process);
        execResultInfo.setOutput(output);

        process.destroy();
        return execResultInfo;
    }
}