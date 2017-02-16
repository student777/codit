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

    @Auth
    @ResponseBody
    @RequestMapping(value="/run", method = RequestMethod.POST)
    public byte[] run(@RequestParam String code, @RequestParam(value = "problem_id") int problemId, @RequestParam(value = "test_case_id") int testCaseId) {
        String result = testService.run(code, problemId, testCaseId);
        try {
            return result.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return "Something wrong... find me".getBytes();
        }
    }


    @Auth
    @ResponseBody
    @RequestMapping(value="{id}/submit", method = RequestMethod.POST)
    public String submit(@PathVariable("id") int problemId, @RequestParam String code, @AuthApplicant ApplicantVo applicantVo) {
        testService.mark(applicantVo, code, problemId);
        return "good job!";
    }
}