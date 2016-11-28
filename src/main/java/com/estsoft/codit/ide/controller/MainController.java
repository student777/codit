package com.estsoft.codit.ide.controller;

import com.estsoft.codit.db.vo.ApplicantVo;
import com.estsoft.codit.ide.annotation.AuthApplicant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }


    // Set session at InstructionInterceptor
    @RequestMapping("/instruction")
    public String instruction(@AuthApplicant ApplicantVo applicantVo) {
        if (applicantVo == null) {
            return "instruction-error";
        }
        return "instruction";
    }
}
