package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import service.MainService;
import vo.ApplicantVo;
import annotation.AuthApplicant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {

    @Autowired
    MainService mainService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public String index(@AuthApplicant ApplicantVo applicantVo) {
        if (applicantVo == null) {
            return "index";
        }
        return "redirect:instruction";
    }


    // Set session at InstructionInterceptor
    @RequestMapping(value="/instruction")
    public String instruction(Model model, @AuthApplicant ApplicantVo applicantVo) {
        if (applicantVo == null) {
            return "instruction-error";
        }
        mainService.getProblemInfoList(model);
        return "instruction";
    }
}