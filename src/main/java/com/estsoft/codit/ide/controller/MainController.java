package com.estsoft.codit.ide.controller;

import com.estsoft.codit.ide.service.MainService;
import com.estsoft.codit.ide.service.TestService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/*
 MainController- index page, instruction page, practice page 3가지의 URL을 처리한다
 IndexController, InstructionController 따로 안만들고 여기다 모아도 코드 별로 안길듯
 */
@Controller
public class MainController {

  @Autowired
  private MainService mainService;

  @Autowired
  private TestService testService;


  /*
   루트 페이지로 ndex page를 보여준다
   parameter의 uuid와 수험생이 입력한 이름 이메일을 받아 DB 확인 후 세션을 부여한다
   */
  @RequestMapping("/")
  public String index() {
    //세션 부여
    mainService.idenfityApplicant();
    return "index";
  }


  /*
   시험 중 유의사항에 대해 안내하는 페이지
   */
  @RequestMapping("/instruction")
  public String instruction() {
    return "instruction";
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
