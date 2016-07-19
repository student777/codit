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

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
    //TODO: 시험 중간에 튕겨서 다시 온 사람 구제 방안
    testService.initializeTest(model, applicantVo);
    return "test";
  }


  /*
  ajax URL
  POST요청을 받으면 작업 내역을 저장해줌
  */
  @Auth
  @ResponseBody
  @RequestMapping("/save")
  public String save(@RequestParam String code, @RequestParam(value="problem_id") int problemId, @AuthApplicant ApplicantVo applicantVo) {
    boolean isInserted = testService.save(code, problemId, applicantVo.getId());
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
  @Auth
  @ResponseBody
  @RequestMapping("/run")
  public byte[] run( @RequestParam(value="problem_id") int problemId, @AuthApplicant ApplicantVo applicantVo, @RequestParam(value="test_case_id") int testCaseId) {
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
  TODO: 시험 페이지에 다시 들어오는 것을 막는다
  */
  @Auth
  @ResponseBody
  @RequestMapping("/submit")
  public void submit(@AuthApplicant ApplicantVo applicantVo) throws IOException, InterruptedException {
    testService.finalize_test(applicantVo);
    testService.mark(applicantVo);
  }
}
