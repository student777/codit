package com.estsoft.codit.ide.controller;

import com.estsoft.codit.db.vo.ApplicantVo;
import com.estsoft.codit.ide.annotation.Auth;
import com.estsoft.codit.ide.annotation.AuthApplicant;
import com.estsoft.codit.ide.service.ResultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/result")
public class ResultController {
  @Autowired
  private ResultService resultService;


  /*
   /result 메인 페이지로 applicant가 시험 결과를 확인할 수있다.
   추후 제출후 채점으로 바뀌면 ajax 로 요청을 받아 mapd형태로 append
   */
  @Auth
  @RequestMapping("")
  public String main(Model model, @AuthApplicant ApplicantVo applicantVo){
    resultService.getResult(model, applicantVo);
    return "result";
  }

  @Auth
  @RequestMapping("/{index}")
  public String showTree(Model model, @AuthApplicant ApplicantVo applicantVo, @PathVariable("index") int index){
    resultService.getTree(model, applicantVo, index);
    return "result-tree";
  }
}
