package executor;

import vo.ProblemVo;
import vo.SourceCodeVo;

import static executor.ExecUtils.WORKSPACE_PATH;

class ExecJava extends Exec {

    ExecJava(SourceCodeVo sourceCodeVo, ProblemVo problemVo) {
        super(sourceCodeVo, problemVo, "/Task.java", "/Main.java");
        this.compileCommand = new String[]{"/usr/bin/javac", "-cp", WORKSPACE_PATH + "sourcecode/" + sourceCodeVo.getId(), "-encoding", "UTF-8", WORKSPACE_PATH + "sourcecode/" + sourceCodeVo.getId() + "/Main.java"};
        this.runtimeCommand = new String[]{"/usr/bin/java", "-cp", WORKSPACE_PATH + "sourcecode/" + sourceCodeVo.getId(), "Main"};
    }
}
