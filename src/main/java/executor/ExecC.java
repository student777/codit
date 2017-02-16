package executor;

import vo.ProblemVo;

import static executor.ExecUtils.WORKSPACE_PATH;

class ExecC extends Exec {

    ExecC(String code, ProblemVo problemVo) {
        super(code, problemVo, "/task.c", "/main.c");
        this.compileCommand = new String[]{"/usr/bin/gcc", "-o", WORKSPACE_PATH + uuid + "/task", WORKSPACE_PATH + uuid + "/main.c"};
        this.runtimeCommand = new String[]{WORKSPACE_PATH + uuid + "/task"};
    }
}
