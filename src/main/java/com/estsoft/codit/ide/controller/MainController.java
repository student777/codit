package com.estsoft.codit.ide.controller;

import com.estsoft.codit.db.vo.ClientVo;
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
   parameter의 uuid와 수험생이 입력한 이름 이메일을 받아 DB 확인 후 세션을 부여한다
   */
  @RequestMapping("/")
  public String index(@RequestParam(value = "ticket", required=true, defaultValue="") String ticket, Model model) {
    if(ticket.equals("")==true){
      return "index-error";
    }
    //세션 부여
    mainService.idenfityApplicant(ticket, model);
    return "index";
  }


  /*
   시험 중 유의사항에 대해 안내하는 페이지
   수험자가 입력한 이메일이 세션에 있는 유저의 정보와 다를 시 에러
   */
  @RequestMapping("/instruction")
  public String instruction(@RequestParam(value = "email", required=true, defaultValue="") String email, Model model, @AuthApplicant ClientVo client) {
    boolean isAuthenticated = mainService.checkEmail(email, client);
    if(isAuthenticated){
      return "instruction";
    }
    return "instruction-error";
  }


  /*
 sandbox(?)
 IDE 콘솔을 그려주는데 cart
 여기서는 저장버튼을 눌러도 저장안된다 물론 유저입장에서는 저장된것 처럼 느끼게 한다
 complie and run, output 콘솔에 append 기능만 지원
 testService를 갖다 쓴다
 */
  @RequestMapping("/practice")
  public String practice() {
    return "practice";
  }


}
