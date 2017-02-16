package executor;

import vo.ProblemVo;

import static executor.ExecUtils.WORKSPACE_PATH;

class ExecJava extends Exec {

    ExecJava(String code, ProblemVo problemVo) {
        super(code, problemVo, "/Task.java", "/Main.java");
        this.compileCommand = new String[]{"/usr/bin/javac", "-cp", WORKSPACE_PATH + uuid, "-encoding", "UTF-8", WORKSPACE_PATH + uuid + "/Main.java"};
        this.runtimeCommand = new String[]{"/usr/bin/java", "-cp", WORKSPACE_PATH + uuid, "Main"};
    }
}
