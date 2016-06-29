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
  IDE 콘솔. applicant 정보와 cart 정보를 받아서 콘솔을 그려준다
  시간 관련..(?)
   */
  @Auth
  @RequestMapping("")
  public String main(Model model, @AuthApplicant ApplicantVo applicantVo, @RequestParam(value = "language", defaultValue="1") int language_id) {
    testService.setProblem(model, applicantVo, language_id);
    return "test";
  }


  /*
  ajax를 위한 URL. POST요청을 받으면 작업 내역을 저장해줌
  */
  @ResponseBody
  @RequestMapping("/save")
  public String save() {
    testService.save();
    return "작업 내역이 저장되었습니다";
  }

  /*
  ajax URL
  저장된 소스코드를 컴파일하고 실행한다 결과를 append해준다
   */
  @ResponseBody
  @RequestMapping("/run")
  public String run() {
    testService.run();
    return "잘 돌아간다 잘잘 돌아간다";
  }

}
