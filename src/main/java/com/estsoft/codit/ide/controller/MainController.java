package com.estsoft.codit.ide.controller;

import com.estsoft.codit.db.vo.ApplicantVo;
import com.estsoft.codit.ide.annotation.Auth;
import com.estsoft.codit.ide.annotation.AuthApplicant;
import com.estsoft.codit.ide.service.MainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/*
 MainController- index page, instruction page, practice page 3가지의 URL을 처리한다
 IndexController, InstructionController 따로 안만들고 여기다 모아도 코드 별로 안길듯
 */
@Controller
public class MainController {

  @Autowired
  private MainService mainService;

  /*
   루트 페이지로 index page를 보여준다
   parameter의 ticket 정보를 받아 DB 확인 후 index page에 이름만 넣어준다
   */
  @RequestMapping("/")
  public String index(@RequestParam(value = "ticket", defaultValue="") String ticket, Model model) {
    if( ticket.equals("") ){
      return "index-error";
    }
    boolean existsTicket = mainService.checkTicket(ticket, model);
    if(existsTicket){
      return "index";
    }
    return "index-error";
  }


   /*
   세션을 부여하고 시험 중 유의사항에 대해 안내하는 페이지
   */
  @RequestMapping("/instruction")
  public String instruction(@RequestParam(value = "email", defaultValue="") String email, @AuthApplicant ApplicantVo applicantVo) {
    //authInterceptor에서 ticket과 email이 맞는지 체크해서 맞으면 세션을 부여하고 @AuthApplicant에 값을 넣어준다
    if(applicantVo==null){
      return "instruction-error";
    }
    return "instruction";
  }


  /*
  applicant가 튜토리얼을 보며 IDE 사용방법을 익히는 페이지
  여기서는 저장버튼을 눌러도 저장안된다 물론 유저입장에서는 저장된것 처럼 느끼게 한다
  complie and run, output 콘솔에 append 기능만 지원
  */
  @Auth
  @RequestMapping("/practice")
  public String practice(Model model) {
    mainService.setPracticeProblem(model);
    return "practice";
  }


}
