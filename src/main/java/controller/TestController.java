package controller;

import vo.ApplicantVo;
import annotation.Auth;
import annotation.AuthApplicant;
import service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@RequestMapping("/test")
@Controller
public class TestController {

    @Autowired
    private TestService testService;


    // get problem_info, problem list, test_case by id of problem_info and render these data
    @Auth
    @RequestMapping("{id}")
    public String main(Model model, @PathVariable("id") int problemInfoId) {
        testService.initializeTest(model, problemInfoId);
        return "test";
    }


    /*
    ajax URL
    POST요청을 받으면 작업 내역을 저장해줌
    */
    @Auth
    @ResponseBody
    @RequestMapping("/save")
    public String save(@RequestParam String code, @RequestParam(value = "problem_id") int problemId, @AuthApplicant ApplicantVo applicantVo) {
        boolean isInserted = testService.save(code, problemId, applicantVo.getId());
        if (isInserted) {
            return "success";
        }
        return "fail";
    }

    /*
    ajax URL
    ajax는 비동기식이므로 source_code를 직접 받아 돌릴수 없음(test.jsp참고)
    applicant_id 와 problem_id 를 받아서 DB에서 식별해줘야 함
    이후 저장된 소스코드를 컴파일하고 실행하여 결과를 response로 쏴준다
     */
    @Auth
    @ResponseBody
    @RequestMapping("/run")
    public byte[] run(@RequestParam(value = "problem_id") int problemId, @AuthApplicant ApplicantVo applicantVo, @RequestParam(value = "test_case_id") int testCaseId) {
        String result = testService.run(problemId, applicantVo.getId(), testCaseId);
        try {
            return result.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "이건 나와서는 안돼".getBytes();
        }
    }


    /*
    ajax UFL
    applicant의 submit_time에 현재 시간을 입력하고
    */
    @Auth
    @ResponseBody
    @RequestMapping("{id}/submit")
    public String submit(@PathVariable("id") int problemId, @AuthApplicant ApplicantVo applicantVo) {
        testService.mark(applicantVo, problemId);
        return "ddd";
    }
}
