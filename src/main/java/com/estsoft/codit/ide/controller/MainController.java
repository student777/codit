package com.estsoft.codit.ide.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/*
 MainController- index page, practice page, instruction page 3가지의 URL을 처리한다
 IndexController, PracticeController 따로 안만들고 여기다 모아도 코드 별로 안길듯
 */
@Controller
public class MainController {

  /**
   * Main string.
   *
   * @return the string
   */
/*
   루트 페이지로 parameter의 uuid를 받아 서비스를 처리하여 index page를 보여준다
   */
  @RequestMapping("/")
  public String index() {
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
   sandbox(?)- front
   */
  @RequestMapping("/practice")
  public String practice() {
    return "practice";
  }





}
