package com.estsoft.codit.ide.executor;

import com.estsoft.codit.db.vo.ProblemVo;
import com.estsoft.codit.db.vo.SourceCodeVo;

public class ExecFactory {

    public Exec pick(int languageId, SourceCodeVo sourceCodeVo, ProblemVo problemVo) {
        Exec exec = null;
        if (languageId == 1) {
            exec = new ExecC(sourceCodeVo, problemVo);
        } else if (languageId == 2) {
            exec = new ExecJava(sourceCodeVo, problemVo);
        } else if (languageId == 3) {
            exec = new ExecPython(sourceCodeVo, problemVo);
        } else {
            // DO something
        }
        return exec;
    }
}
