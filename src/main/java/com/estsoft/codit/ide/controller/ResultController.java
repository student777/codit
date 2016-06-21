package com.estsoft.codit.ide.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/result")
public class ResultController {
  @RequestMapping("")
  public String main() {
    return "result";
  }
}
