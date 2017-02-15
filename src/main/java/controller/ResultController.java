package controller;

import org.springframework.web.bind.annotation.RequestMethod;
import vo.ApplicantVo;
import annotation.Auth;
import annotation.AuthApplicant;
import service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/result", method = RequestMethod.GET)
public class ResultController {
    @Autowired
    private ResultService resultService;


    /*
     /result 메인 페이지로 applicant가 시험 결과를 확인할 수있다.
     추후 제출후 채점으로 바뀌면 ajax 로 요청을 받아 map형태로 append
     */
    @Auth
    @RequestMapping("")
    public String list() {
        // I don't know what to add this page
        return "result";
    }

    @Auth
    @RequestMapping("/{id}")
    public String detail(Model model, @AuthApplicant ApplicantVo applicantVo, @PathVariable("id") int problemId) {
        resultService.getDetailResult(model, applicantVo, problemId);
        return "result";
    }
}
