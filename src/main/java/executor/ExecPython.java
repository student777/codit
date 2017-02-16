package executor;

import vo.ProblemVo;
import vo.TestCaseVo;

import static executor.ExecUtils.WORKSPACE_PATH;


class ExecPython extends Exec {

    ExecPython(String code, ProblemVo problemVo) {
        super(code, problemVo, "/task.py", "/main.py");
        this.runtimeCommand = new String[]{"python3", WORKSPACE_PATH + uuid + "/main.py"};
    }

    //python만 유일하게 컴파일 과정이 없으므로 override해줌
    @Override
    public String run(TestCaseVo testCaseVo) {
        return execCommand(runtimeCommand, testCaseVo, false).getOutput();
    }

    @Override
    public ExecResultInfo mark(TestCaseVo testCaseVo) {
        ExecResultInfo execResultInfo = execCommand(runtimeCommand, testCaseVo, true);
        return execResultInfo;
    }
}
