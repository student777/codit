package com.estsoft.codit.ide.controller;

import com.estsoft.codit.db.vo.ApplicantVo;
import com.estsoft.codit.ide.annotation.Auth;
import com.estsoft.codit.ide.annotation.AuthApplicant;
import com.estsoft.codit.ide.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/test")
@Controller
public class TestController {

  @Autowired
  private TestService testService;


  /*
  IDE 콘솔. applicant, cart, problem, problem_info, test_case 정보를 받아서 콘솔을 그려준다
   */
  @Auth
  @RequestMapping("")
  public String main(Model model, @AuthApplicant ApplicantVo applicantVo) {
    testService.initializeTest(model, applicantVo);
    return "test";
  }


  /*
  ajax를 위한 URL. POST요청을 받으면 작업 내역을 저장해줌
  */
  @ResponseBody
  @RequestMapping("/save")
  public String save(@RequestParam String code, @RequestParam(value="problem_id") int problemId, @RequestParam(value="applicant_id") int applicantId) {
    boolean isInserted = testService.save(code, problemId, applicantId);
    if(isInserted){
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
  @ResponseBody
  @RequestMapping("/run")
  public String run( @RequestParam(value="problem_id") int problemId, @RequestParam(value="applicant_id") int applicantId, @RequestParam(value="test_case_id") int testCaseId) {
    String result = testService.run(problemId, applicantId, testCaseId);
    return result;
  }

}
