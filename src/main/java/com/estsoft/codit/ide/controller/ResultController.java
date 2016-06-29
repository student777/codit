package com.estsoft.codit.ide.controller;

import com.estsoft.codit.ide.annotation.Auth;
import com.estsoft.codit.ide.service.ResultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/result")
public class ResultController {
  @Autowired
  private ResultService resultService;

  @Auth
  @RequestMapping("")
  public String main() {
    Map map = resultService.getResult( /*사용자 uuid*/ );
    return "result";
  }
}
