package controller;

import annotation.Auth;
import annotation.AuthApplicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.TestService;
import vo.ApplicantVo;

import java.io.UnsupportedEncodingException;

@RequestMapping("/test")
@Controller
public class TestController {

    @Autowired
    private TestService testService;


    // get problem_info, problem list, test_case by id of problem_info and render these data
    @Auth
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public String main(Model model, @PathVariable("id") int problemInfoId) {
        testService.initializeTest(model, problemInfoId);
        return "test";
    }


    /*
    <ajax URL>
    ajax는 비동기식이므로 source_code를 직접 받아 돌릴수 없음(test.jsp참고)
    applicant_id 와 problem_id 를 받아서 DB에서 식별해줘야 함
    이후 저장된 소스코드를 컴파일하고 실행하여 결과를 response로 쏴준다
     */
    @Auth
    @ResponseBody
    @RequestMapping(value="/run", method = RequestMethod.POST)
    public byte[] run(@RequestParam String code, @RequestParam(value = "problem_id") int problemId, @AuthApplicant ApplicantVo applicantVo, @RequestParam(value = "test_case_id") int testCaseId) {
        testService.save(code, problemId, applicantVo.getId());
        String result = testService.run(problemId, applicantVo.getId(), testCaseId);
        try {
            return result.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "Something wrong... find me".getBytes();
        }
    }


    /*
    <ajax UFL>
    applicant의 submit_time에 현재 시간을 입력하고
    */
    @Auth
    @ResponseBody
    @RequestMapping(value="{id}/submit", method = RequestMethod.POST)
    public String submit(@PathVariable("id") int problemId, @AuthApplicant ApplicantVo applicantVo) {
        testService.mark(applicantVo, problemId);
        return "ddd";
    }
}