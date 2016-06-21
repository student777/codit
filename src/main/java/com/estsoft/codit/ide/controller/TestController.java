package com.estsoft.codit.ide.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/test")
@Controller
public class TestController {
  @RequestMapping("")
  public String main() {
    return "test";
  }
}
