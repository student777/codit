package executor;

import vo.ProblemVo;

public class ExecFactory {

    public Exec pick(int languageId, String code, ProblemVo problemVo) {
        Exec exec = null;
        if (languageId == 1) {
            exec = new ExecC(code, problemVo);
        } else if (languageId == 2) {
            exec = new ExecJava(code, problemVo);
        } else if (languageId == 3) {
            exec = new ExecPython(code, problemVo);
        } else {
            // DO something
        }
        return exec;
    }
}
