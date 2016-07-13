package com.estsoft.codit.ide.controller;

import com.estsoft.codit.db.vo.ApplicantVo;
import com.estsoft.codit.ide.annotation.Auth;
import com.estsoft.codit.ide.annotation.AuthApplicant;
import com.estsoft.codit.ide.service.ResultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/result")
public class ResultController {
  @Autowired
  private ResultService resultService;


  /*
   /result 메인 페이지로 applicant가 시험 결과를 확인할 수있다.
   */
  @Auth
  @RequestMapping("")
  public String main(Model model, @AuthApplicant ApplicantVo applicantVo){
    model.addAttribute("applicantVo", applicantVo);
    return "result";
  }

  /*
   ajax URL
   ajax 요청을 받아 시험 결과를 DB에서 map형태로 가져온다
   */
  @Auth
  @RequestMapping("get_data")
  public Map getData(@AuthApplicant ApplicantVo applicantVo){
    return resultService.getResult(applicantVo);
  }

}
